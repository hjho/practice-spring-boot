package hjho.prj.prct.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	

}
