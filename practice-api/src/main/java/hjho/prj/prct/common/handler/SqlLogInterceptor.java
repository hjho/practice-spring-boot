package hjho.prj.prct.common.handler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Intercepts({
	@Signature(type=Executor.class, args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }, method = "query")
	,@Signature(type=Executor.class, args = { MappedStatement.class, Object.class }, method = "update")
	,@Signature(type=StatementHandler.class, args = { Statement.class, ResultHandler.class }, method = "query")
	,@Signature(type=StatementHandler.class, args = { Statement.class }, method = "update")
})
public class SqlLogInterceptor implements Interceptor {

	private boolean isLog = false;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if(isLog) log.debug("[SQL] Log Intercept");
		if(invocation.getTarget() instanceof Executor) {
			return interceptExecutor(invocation);
		} else {
			return interceptStatementHandler(invocation);
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
		log.debug("[SQL] : {}", mstmt.getId());
		return invocation.proceed();
	}

	private Object interceptStatementHandler(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
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
							sql = sql.replaceFirst("\\?", "'".concat((String) field.get(param)).concat("'"));
						} else {
							sql = this.replaceSql(sql, field.get(param));
						}
					}
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("[SQL] Error : {}", e.getMessage());
		}
		log.debug("\n[SQL] : {}\n{}", sqlId, sql);
		return invocation.proceed();
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
}
