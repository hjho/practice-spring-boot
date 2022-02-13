package hjho.prj.prct.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookieUtil {
	
	/***
	 * Cookie Set. 
	 */
	public static boolean setCookie(HttpServletResponse response, String name, String value, String comment) {
		
		Cookie cookie = new Cookie(name, value);
		// 쿠키의 유효시간 설정 (0으로 할 경우 무효화)
		// cookie.setMaxAge(24 * 60 * 60);		// 1일, 지정하지 않는다면 브라우저가 닫힐 시 삭제.
		// 쿠키의 사용 유효 디렉토리 설정
		cookie.setPath("/");
		// 쿠키의 버전 정보 설정.
		cookie.setVersion(1);
		// 쿠키의 코멘트 설정.
		cookie.setComment(comment);
		// HTTPS에서만 사용. > true
		cookie.setSecure(false);
		// 웹브라우저에서 스크립트 접근X > true 
		cookie.setHttpOnly(true);
		
		response.addCookie(cookie);
		log.debug("[ Cookie ] Set OK, NAME : {}", name);
		return true;
	}
	
	/***
	 * Cookie Get Value. 
	 */
	public static String getCookieVal(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if(name.equals(cookieName)) {
				log.debug("[ Cookie ] Get OK, NAME : {}", name);
				return cookie.getValue();
			}
		}
		log.debug("[ Cookie ] Get Fail, NAME : {}", name);
		return null;
	}
	
	/***
	 * Cookie Remove. 
	 */
	public static boolean removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		boolean isOk = false;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if(name.equals(cookieName)) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				isOk = true;
				break;
			}
		}
		if(isOk) {
			log.debug("[ Cookie ] Remove OK, NAME : {}", name);
		} else {
			log.debug("[ Cookie ] Remove Fail, NAME : {}", name);
		}
		return isOk;
	}
	
}
