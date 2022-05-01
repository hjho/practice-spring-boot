package hjho.prj.prct.common.filter;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleXssFilter {

	public static String cleanPatameter(String name, String value) {
		
		String returnStr = clean(value);
		if(!returnStr.equals(value)) {
			log.debug("[F] CLEAN [ {} : {} >> {} ]", name, value, returnStr);
		}
		return returnStr;
	}
	
	public static String clean(String value) {
		String cleanVal = value;
		
		if(StringUtils.isNotEmpty(cleanVal)
//	    && !"script-include".equals(cleanVal)
//	    && !"init-script-include".equals(cleanVal)
	    ) {
			cleanVal = cleanVal.replaceAll("(?i)eval\\((.*)\\)", "");
			cleanVal = cleanVal.replaceAll("(?i)[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
			cleanVal = cleanVal.replaceAll("(?i)script", "_script_");
			cleanVal = cleanVal.replaceAll("(?i)alert", "_alert_");
			cleanVal = cleanVal.replaceAll("(?i)confirm", "_confirm_");
			cleanVal = cleanVal.replaceAll("(?i)prompt", "_prompt_");
			cleanVal = cleanVal.replaceAll("(?i)onmouseover", "_onmouseover_");
			cleanVal = cleanVal.replaceAll("(?i)onload", "_onload_");
			cleanVal = cleanVal.replaceAll("(?i)onerror", "_onerror_");
			cleanVal = cleanVal.replaceAll("(?i)function", "_function_");
			// 2022-03-01 메뉴....
			// cleanVal = cleanVal.replaceAll("(?i)location", "_location_");
			cleanVal = cleanVal.replaceAll("(?i)window", "_window_");
			cleanVal = cleanVal.replaceAll("(?i)cookie", "_cookie_");
			
			cleanVal = cleanVal.replaceAll("(?i)img", "_img_");
			cleanVal = cleanVal.replaceAll("(?i)src", "_src_");
			cleanVal = cleanVal.replaceAll("(?i)href", "_href_");
		} else {
			cleanVal = "";
		}
		
		return cleanVal;
	}
}
