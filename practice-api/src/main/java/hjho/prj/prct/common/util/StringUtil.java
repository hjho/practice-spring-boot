package hjho.prj.prct.common.util;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
	
	/**
	 * Yn이 "Y"일 경우만 true 
	 */
	public static boolean isY(String yn) {
		if(StringUtils.isEmpty(yn)) {
			return false;
		} 
		
		boolean isY = false;
		if("Y".equals(yn)) {
			isY = true;
		}
		return isY;
	}
	/**
	 * Yn이 "Y"가 아닐 경우만 true 
	 */
	public static boolean isNotY(String yn) {
		return !StringUtil.isY(yn);
	}
	
	/**
	 * str이 Null or "" 일 경우 ""
	 */
	public static String NVL(String str) {
		return StringUtil.NVL(str, "");
	}
	/**
	 * str이 Null or "" 일 경우 defStr
	 */
	public static String NVL(String str, String defStr) {
		if(StringUtils.isEmpty(str)) {
			return defStr;
		}
		return str;
	}
	/**
	 * str을 length 수 만큼 문자열의 길이를 fill로 오른쪽에 채운다.
	 * @param str : 123
	 * @param length : 5
	 * @param fill : 0
	 * @return "12300"
	 */
	public static String RPAD(String str, int length, String fill) {
		char[] chr = (StringUtils.isNotEmpty(str)) ? str.toCharArray() : null;
		fill = StringUtil.NVL(fill, " ");
		
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<length; i++) {
			if(chr != null && i < chr.length) {
				sb.append(chr[i]);
			} else {
				sb.append(fill);
			}
		}
		return sb.toString();
	}
	/**
	 * str을 length 수 만큼 문자열의 길이를 fill로 왼쪽에 채운다.
	 * @param str : 123
	 * @param length : 5
	 * @param fill : 0
	 * @return "00123"
	 */
	public static String LPAD(String str, int length, String fill) {
		char[] chr = (StringUtils.isNotEmpty(str)) ? str.toCharArray() : null;
		fill = StringUtil.NVL(fill, " ");
		
		StringBuffer sb = new StringBuffer();
		int chrLen = (chr != null) ? chr.length : 0;
		if(length >= chrLen) {
			for(int i=length; i>0; i--) {
				if(chr != null && i <= chrLen) {
					sb.append(chr[chrLen-i]);
				} else {
					sb.append(fill);
				}
			}
		} else {
			for(int i=0; i<length; i++) {
				if(chr != null && i < chr.length) {
					sb.append(chr[i]);
				} else {
					sb.append(fill);
				}
			}
		}
		return sb.toString();
	}
	
	public static String camelToUnder(String str) {
		if(StringUtils.isEmpty(str)) return null;
		
		String regex = "([a-z])([A-Z]+)";
		String replacement = "$1_$2";
		return str.replaceAll(regex, replacement).toLowerCase();
	}

	/**
	 * 입력된 문자가 숫자인지 검증.
	 */
	public static boolean isNumber(String value) {
		return Pattern.matches("^[0-9]*$", value);
	}
	/**
	 * 입력된 문자가 영어 소,대문자 인지 검증.
	 */
	public static boolean isEnLang(String value) {
		return Pattern.matches("^[a-zA-Z]*$", value);
	}
	/**
	 * 입력된 문자가 한국어인지 검증.
	 */
	public static boolean isKoLang(String value) {
		return Pattern.matches("^[가-힣]*$", value);
	}
	/**
	 * 입력된 문자가 이메일 패턴인지 검증.
	 */
	public static boolean isEmail(String value) {
		return Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", value);
	}
	/**
	 * 입력된 문자가 전화번호 패턴인지 검증.
	 */
	public static boolean isTelNum(String value) {
		return Pattern.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$", value);
	}
	/**
	 * 입력된 문자가 핸드폰번호 패턴인지 검증.
	 */
	public static boolean isPhoneNum(String value) {
		return Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", value);
	}
	/**
	 * 입력된 문자가 주민번호 패턴인지 검증.
	 */
	public static boolean isResidentNum(String value) {
		return Pattern.matches("\\d{6}\\-[1-4]\\d{6}", value);
	}
	/**
	 * 입력된 문자가 우편번호 패턴인지 검증.
	 */
	public static boolean isPostNum(String value) {
		return Pattern.matches("^\\d{5}$", value);
	}
}
