package hjho.prj.prct.common.handler;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import hjho.prj.prct.biz.main.service.MainService;
import hjho.prj.prct.common.exception.AuthVerifyException;
import hjho.prj.prct.common.exception.SessionExpirationException;
import hjho.prj.prct.common.util.SessionUtil;
import hjho.prj.prct.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableWebMvc
public class HttpServletInterceptor implements HandlerInterceptor {
	
	private final boolean isHeaderLog = true;
	
	private final boolean isCookieLog = true;
	
	private final boolean isSessionLog = true;
	
	private final boolean isSecurityLog = true;
	
	@Autowired
	private MainService mainService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	throws Exception 
	{
		log.info("============= [INTERCEPTOR STRART] ================");
		log.debug("[H] [ Http BEFORE  ] : {}", request.getRequestURL().toString());
		StopWatch stopWatch = new StopWatch(request.getRequestURL().toString());
		
		stopWatch.start("log");
		// Header Log
		if(isHeaderLog) this.hearderLog(request);
		// Cookie Log		
		if(isCookieLog) this.cookieLog(request);
		// Session Log
		if(isSessionLog) this.sessionLog(request);
		stopWatch.stop();
		
		// 세션 만료 검증. >> 로그인 OK or 사용자 SET
		stopWatch.start("loginSessionCheck");
		if(mainService.isSessionFail(request)) {
			throw new SessionExpirationException();
		} else {
			// Set User Info
			this.initUserInfo(request);
		}
		stopWatch.stop();

		// 토큰 검증 및 재발급.
		stopWatch.start("tokenCheck");
		/*
		if(mainService.isTokenVerifyFail(request)) {
			throw new SessionExpirationException(); // 다른 EXCEPTION "유효하지 않은 토큰입니다."
		}*/
		stopWatch.stop();
		
		// 사용자 접속 권한 체크. 조회, 등록, 수정, 삭제 authority
		stopWatch.start("authorityCheck");
		/**/
		if(mainService.isMgrAuthorityFail(request)) {
			throw new AuthVerifyException(); // "권한이 없습니다."
		}
		stopWatch.stop();
		
		stopWatch.start("runningTime");
		request.setAttribute("STOP_WATCH", stopWatch);
		return true;
	}

	private void hearderLog(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		log.debug("============= [HEADER LOG START] ================");
		while(headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String header = request.getHeader(headerName);
			if(headerName.length() > 20) {
				log.debug("=== {} : {}", StringUtil.RPAD(headerName, 30, ""), header);
			} else {
				log.debug("=== {} : {}", StringUtil.RPAD(headerName, 20, ""), header);
			}
		}
		log.debug("============= [HEADER LOG END] ==================");
	}

	private void cookieLog(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		log.debug("============= [COOKIE LOG START] ================");
		for (Cookie cookie : cookies) {
			log.debug("=== {} : {}", StringUtil.RPAD(cookie.getName(), 20, ""), cookie.getValue());
		}
		log.debug("============= [COOKIE LOG END]===================");
	}
	
	private void sessionLog(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> sessionNames = session.getAttributeNames();
		log.debug("============= [SESSION LOG START] ================");
		log.debug("=== {} : {}", StringUtil.RPAD("session-id"        , 20, ""), session.getId());
		log.debug("=== {} : {}", StringUtil.RPAD("creation-time"     , 20, ""), SessionUtil.getCreationTime(session));
		log.debug("=== {} : {}", StringUtil.RPAD("last-accessed-time", 20, ""), SessionUtil.getLastAccessedTime(session));
		log.debug("=== {} : {}", StringUtil.RPAD("destroy-time"      , 20, ""), SessionUtil.getDestroyTime(session));
		log.debug("=== {} : {}", StringUtil.RPAD("destroy-set-time"  , 20, ""), SessionUtil.getDestroySetTime(session));
		while(sessionNames.hasMoreElements()) {
			String sessionName = sessionNames.nextElement();
			Object sessionObj = session.getAttribute(sessionName);
			log.debug("=== {} : {}", StringUtil.RPAD(sessionName     , 20, ""), sessionObj);
		}
		log.debug("============= [SESSION LOG END]===================");
	}
	
	/** SecurityContextHolder
	 * <pre>
	 * Authentication
	 *      - 인증정보(요청 or 결과)를 가지는 인터페이스, isAuthenticated 를 통해 인증 결과를 알수 있음.
	 * Collection<? extends GrantedAuthority> getAuthorities()
	 *      - 인증된 주체 Principal이 가지고 있는 권한들. AuthenticationManager에 의해 SET 됨.
	 * Object getCredentials()
	 *      - 인증된 주체 Principal의 정확함을 증명하는 값.
	 *      - 일반적으로 암호이지만 Authentication 과 관련된 모든 것이 될 수 있음.
	 *      - 인증이 완료되면 데이터를 제거 한다.
	 * Object getDetails() 
	 *      - 추가적으로 세부사항을 저장할 수 있는 정보. IP, 인증서 시리얼 넘버 같은 값.
	 * Object getPrincipal()
	 *      - 인증된 주체의 식별자, 인증정보를 가지고 있음.
	 *      - 많은 인증 프로바이더들은 UserDetails 객체로 반환을 함.
	 * boolean isAuthenticated()
	 *      - 인증이 되었는지 안되어있는지 확인하는 메소드.
	 * </pre>
	 * 자주 사용하는 구현체는 UsernamePasswordAuthenticationToken 일 듯?
	 * @param request
	 * @return
	 */
	private boolean initUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Authentication authentication = new TestingAuthenticationToken(SessionUtil.getMgrInfo(session)
				                                                     , SessionUtil.getToken(session)
				                                                     , new String[] {"CRET", "READ", "UPD", "DEL"});
		
		SecurityContext context = SecurityContextHolder.createEmptyContext(); 
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
		
		if(isSecurityLog)  {
			log.debug("============= [SECURITY LOG START] ================");
			log.debug("=== {} : {}", StringUtil.RPAD("authenticated", 20, ""), authentication.isAuthenticated());
			log.debug("=== {} : {}", StringUtil.RPAD("principal", 20, ""), authentication.getPrincipal());
			log.debug("=== {} : {}", StringUtil.RPAD("credentials", 20, ""), authentication.getCredentials());
			log.debug("=== {} : {}", StringUtil.RPAD("authorities", 20, ""), authentication.getAuthorities());
			log.debug("============= [SECURITY LOG END] ==================");
		}
		
		return true;
	}
	
	private void delUserInfo() {
		SecurityContextHolder.clearContext();
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			               @Nullable ModelAndView modelAndView)
	throws Exception 
	{
		StopWatch stopWatch = (StopWatch) request.getAttribute("STOP_WATCH");
		stopWatch.stop();
		log.debug("[H] [ Http AFTER   ] : {}", request.getRequestURL().toString());
		this.delUserInfo();
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			                    @Nullable Exception ex) 
	throws Exception 
	{
		log.debug("[H] [ Http COMPLET ] : {}", request.getRequestURL().toString());
		StopWatch stopWatch = (StopWatch) request.getAttribute("STOP_WATCH");
		log.debug("## {}", stopWatch.shortSummary());
		log.debug("## {}", stopWatch.getTotalTimeMillis());
		log.debug("\n{}", stopWatch.prettyPrint());
		log.info("============= [INTERCEPTOR END] ===================");
	}
}
