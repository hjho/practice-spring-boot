package hjho.prj.prct.common.handler;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.interfazz.CryptoType;
import hjho.prj.prct.common.interfazz.CryptoType.Type;
import hjho.prj.prct.common.util.CryptoUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Intercepts({
	// SQL 실행
	  @Signature(type=Executor.class, args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }, method = "query")
	, @Signature(type=Executor.class, args = { MappedStatement.class, Object.class }, method = "update")
    // SQL PARAMETER ENCODE
    , @Signature(type=StatementHandler.class, args = { Statement.class }, method = "parameterize")
	// SQL LOG, SQL PARAMETER DECODE
	, @Signature(type=StatementHandler.class, args = { Statement.class, ResultHandler.class }, method = "query")
	, @Signature(type=StatementHandler.class, args = { Statement.class }, method = "update")
	// SQL RESULT SET
	// , @Signature(type=ResultSetHandler.class, args = { Statement.class }, method = "handleResultSets")
})
public class SqlLogInterceptor implements Interceptor {

	private boolean isLog = false;
	
	@Autowired
	private CryptoUtils cryptoUtils;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		String methodName = invocation.getMethod().getName();
		if(isLog) {
			log.debug("[SQL] Crypto Intercept Method: {}", methodName);
			log.debug("[SQL] Crypto Intercept Class : {}", invocation.getTarget().getClass());
		}
		
		if(invocation.getTarget() instanceof Executor) {
			return interceptExecutor(invocation);
			
		} else {
			// ENC 파라미터 암호화.
			if("parameterize".equals(methodName)) {
				return interceptParameterize(invocation);
				
			// 1. SQL QUERY LOG 
			// 2. 암호화된 ENC 파라미터 원복
			// 3. Set Result Value
			} else {
				return interceptStatementHandler(invocation);
			}
		}
		
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		log.debug("[SQL] Set Properties");
	}
	
	private Object interceptExecutor(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
		MappedStatement mstmt = (MappedStatement) invocation.getArgs()[0];
		MDC.put("SQL-ID", mstmt.getId());
		if(isLog) {
			log.debug("[SQL] : {}", mstmt.getId());
		}
		return invocation.proceed();
	}
	
	private Object interceptParameterize(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		ParameterHandler parameterHandler = statementHandler.getParameterHandler();
		
		Object param = parameterHandler.getParameterObject();
		try {
			if(param != null) {
				// Integer, Long, Float, Double, String, Map
				if(param instanceof Integer
				|| param instanceof Long
				|| param instanceof Float
				|| param instanceof Double
				|| param instanceof String
				|| param instanceof Map
				|| param instanceof List
				) {
					// VO가 아닌 얘들은 암복호화 안함.
				} else {
					BoundSql boundSql = statementHandler.getBoundSql();
					// SQL에서 읽어온 변수 
					List<ParameterMapping> parameterMapping = boundSql.getParameterMappings();
					for(ParameterMapping map : parameterMapping) {
						String key = map.getProperty();
						Class<?> javaType = map.getJavaType();
						
						Field field = this.getDeclaredField(param.getClass(), key);
						
						// 필드 접근 허용
						field.setAccessible(true);
						
						// SQL에서 읽어온 변수 중. Annotation이 CryptoType인 변수.
						CryptoType cryptoType = field.getAnnotation(CryptoType.class);
						if(cryptoType != null && CryptoType.Division.ENC.equals(cryptoType.dv())) {
							// ENC인 변수 중 값이 있는 변수.
							if(javaType == String.class && ObjectUtils.isNotEmpty(field.get(param))) {
								String val = (String) field.get(param);
								String encVal = this.encSqlParam(cryptoType.tp(), val);
								
								field.set(param, encVal);
								if(isLog) {
									log.debug("[SQL] Crypto Intercept Encode: \"{}\" = '{}' > '{}'", key, val, encVal);
								}
							}
						}
						
						field.setAccessible(false);
					}
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("[SQL] Error : {}", e.getMessage());
		}
		return invocation.proceed();
	}
	
	@SuppressWarnings("unchecked")
	private Object interceptStatementHandler(Invocation invocation) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException, UserException {
		String sqlId = MDC.get("SQL-ID");
		MDC.remove("SQL-ID");
		
		Object param = null;
		Object[] args = invocation.getArgs();
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		
		if(args.length >= 1 && args[0] != null && args[0] instanceof PreparedStatement) {
			ParameterHandler parameterHandler = statementHandler.getParameterHandler();
			param = parameterHandler.getParameterObject();
		}
		
		BoundSql boundSql = statementHandler.getBoundSql();
		String sql = boundSql.getSql();
		
		try {
			if(param != null) {
				// Integer, Long, Float, Double
				if(param instanceof Integer
				|| param instanceof Long
				|| param instanceof Float
				|| param instanceof Double
				) {
					sql = this.replaceSql(sql, param);
					
				// String
				} else if(param instanceof String) {
					sql = sql.replaceFirst("\\?", "'".concat((String) param).concat("'"));
				
				// Map
				} else if(param instanceof Map) {
					List<ParameterMapping> parameterMapping = boundSql.getParameterMappings();
					
					String key = null;
					Object val = null;
					for(ParameterMapping map : parameterMapping) {
						key = map.getProperty();
						val = ((Map<?, ?>) param).get(key);
						if(val instanceof String) {
							sql = sql.replaceFirst("\\?", "'".concat((String) val).concat("'"));
						} else {
							sql = this.replaceSql(sql, val);
						}
					}
					
				// VO
				} else {
					List<ParameterMapping> parameterMapping = boundSql.getParameterMappings();
					Class<? extends Object> paramClass = param.getClass();
					
					for(ParameterMapping map : parameterMapping) {
						String key = map.getProperty();
						Class<?> javaType = map.getJavaType();
						
						Field field = this.getDeclaredField(paramClass, key);
						
						field.setAccessible(true);
						
						if(String.class == javaType) {
							String val = (String) ObjectUtils.defaultIfNull(field.get(param), "");
							sql = sql.replaceFirst("\\?", "'".concat(val).concat("'"));
							
							// SQL에서 읽어온 변수 중. Annotation이 CryptoType인 변수.
							CryptoType cryptoType = field.getAnnotation(CryptoType.class);
							if(cryptoType != null && CryptoType.Division.ENC.equals(cryptoType.dv())) {
								String decVal = this.decSqlParam(cryptoType.tp(), val);
								field.set(param, decVal);
								if(isLog) {
									log.debug("[SQL] Crypto Intercept Decode: \"{}\" = '{}' > '{}'", key, val, decVal);
								}
							}
							
						} else {
							sql = this.replaceSql(sql, field.get(param));
						}
						
						field.setAccessible(false);
					}
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("[SQL] Error : {}", e.getMessage());
		}
		
		log.debug("\n[SQL] : {}\n{}", sqlId, sql);
		
		List<Object> list = null;
		String methodName = invocation.getMethod().getName();
		try {
			if("query".equals(methodName)) {
				list = statementHandler.query((Statement) invocation.getArgs()[0], (ResultHandler<Object>) invocation.getArgs()[1]);
				for (Object object : list) {
					
					Field[] fields = object.getClass().getDeclaredFields();
					
					for (Field field : fields) {
						if(String.class == field.getGenericType()) {
							// SQL에서 읽어온 변수 중. Annotation이 CryptoType인 변수.
							CryptoType cryptoType = field.getAnnotation(CryptoType.class);
							if(cryptoType != null) {
								field.setAccessible(true);
								String val = (String) ObjectUtils.defaultIfNull(field.get(object), "");
								if(StringUtils.isNotEmpty(val)) {
									String changeVal = val;
									
									// DEC인 변수 중 값이 있는 변수.
									if(CryptoType.Division.DEC.equals(cryptoType.dv())) {
										changeVal = this.decSqlParam(cryptoType.tp(), val);
									// DEC인 변수 중 값이 있는 변수.
									} else if(CryptoType.Division.ENC.equals(cryptoType.dv())) {
										changeVal = this.encSqlParam(cryptoType.tp(), val);
									}
									
									if(!val.equals(changeVal)) {
										if(isLog) {
											log.debug("[SQL] Field Name : \"{}\" = '{}' > '{}'", field.getName(), val, changeVal);
										}
										field.set(object, changeVal);
									}
								}
								field.setAccessible(false);
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if("query".equals(methodName)) {
			return list;
		} else {
			return invocation.proceed();
		}
	}
	
	private String replaceSql(String sql, Object param) {
		if(param != null) {
			return sql.replaceFirst("\\?", param.toString());
		} else {
			return sql.replaceFirst("\\?", "NULL");
		}
	}
	
	private Field getDeclaredField(Class<? extends Object> paramClass, String key) throws NoSuchFieldException {
		Field field = null;
		try {
			// VO
			field = paramClass.getDeclaredField(key);
		} catch (NoSuchFieldException e) {
			// Super VO
			if(paramClass.getSuperclass() != null) {
				return getDeclaredField(paramClass.getSuperclass(), key);
			} else {
				throw e;
			}
		}
		return field;
	}
	
	private String encSqlParam(Type type, String val) throws UserException, UnsupportedEncodingException {
		if(type == null || StringUtils.isEmpty(val)) {
			return val;
		}
		
		String cryptoVal = "";
		if(type.equals(CryptoType.Type.AES)) {
			cryptoVal = cryptoUtils.aesEncrypt(val);
			
		} else if(type.equals(CryptoType.Type.URI)) {
			cryptoVal = URLEncoder.encode(val, "UTF-8");
			
		} else if(type.equals(CryptoType.Type.B64)) {
			cryptoVal = Base64.getEncoder().encodeToString(val.getBytes());
			
		} else {
			cryptoVal = val;
		}
		
		return cryptoVal;
	}
	
	private String decSqlParam(Type type, String val) throws UserException, UnsupportedEncodingException {
		if(type == null || StringUtils.isEmpty(val)) {
			return val;
		}
		
		String cryptoVal = "";
		if(type.equals(CryptoType.Type.AES)) {
			cryptoVal = cryptoUtils.aesDecrypt(val);
			
		} else if(type.equals(CryptoType.Type.URI)) {
			cryptoVal = URLDecoder.decode(val, "UTF-8");
			
		} else if(type.equals(CryptoType.Type.B64)) {
			cryptoVal = new String(Base64.getDecoder().decode(val.getBytes()));
			
		} else {
			cryptoVal = val;
		}
		
		return cryptoVal;
	}
	
}
