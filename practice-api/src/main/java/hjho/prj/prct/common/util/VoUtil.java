package hjho.prj.prct.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.ApiModelProperty;

public class VoUtil {
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> objToMap(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		if(ObjectUtils.isEmpty(obj)) {
			return new HashMap<String, Object>();
		}
		return objectMapper.convertValue(obj, HashMap.class);
	}
	
	public static <T> Object objToVO(Object obj, Class<?> T) {
		if(ObjectUtils.isEmpty(obj)) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.convertValue(obj, T);
	}
	
	public static boolean inputReqValOk(Object vo) throws UserException {
		System.out.println();
		if(vo == null) {
			// System.out.println("ERROR : (9001)");
			throw new UserException("9001");
		}
		Map<String, Object> copyVO = VoUtil.objToMap(vo);
		// 기본 VO
		if(VoUtil.classCheck(vo.getClass(), copyVO)) {
			// DB VO
			if(VoUtil.classCheck(vo.getClass().getSuperclass(), copyVO)) {
				// COMMON VO
				if(VoUtil.classCheck(vo.getClass().getSuperclass().getSuperclass(), copyVO)) {
					// ????
					if(VoUtil.classCheck(vo.getClass().getSuperclass().getSuperclass().getSuperclass(), copyVO)) {
						
					}
				}
			}
		}
		return true;
	}
	
	private static boolean classCheck(Class<? extends Object> clazz, Map<String, Object> copyVO) throws UserException {
		// 첫번째. 기본 클래스(기본객체)
		Class<? extends Object> objectC = clazz;
		if(objectC != null) {
			Field[] objectF = objectC.getDeclaredFields();
			for (Field field : objectF) {
				ApiModelProperty property = field.getAnnotation(ApiModelProperty.class);
				if(property != null) {
					Object val = copyVO.get(property.name());
					
					// 필수입력값이지만. 입력값이 없을 때.
					if(property.required() && ObjectUtils.isEmpty(val)) {
						// System.out.println("ERROR : (9002) " + property.value());
						throw new UserException("9002", new String[] {property.value()});
					}
					
					// 허용된 길이가 있고, 값이 있을 때.
					if(StringUtils.isNotEmpty(property.allowableValues()) && ObjectUtils.isNotEmpty(val)) {
						String lengthStr = "length=";
						String allows[] = property.allowableValues().split("\\,");
						for (String allow : allows) {
							if(allow != null && allow.indexOf(lengthStr) > -1) {
								int valLen = 0;
								if(val instanceof String) {
									valLen = ((String) val).length();
								} else if(val instanceof Long) {
									valLen = Long.toString((Long) val).length();
								} else if(val instanceof Integer) {
									valLen = Integer.toString((Integer) val).length();
								}
								
								// 길이 비교. 
								int maxLen = Integer.parseInt(allow.trim().substring(lengthStr.length()));
								if(valLen > maxLen) {
									// System.out.println("ERROR : (길이) " + valLen + " > " + maxLen);
									throw new UserException("9007", new String[] {property.value(), Integer.toString(maxLen), Integer.toString(valLen)});
								}
							}
						}
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
}
