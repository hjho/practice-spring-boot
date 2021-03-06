package hjho.prj.prct.common.handler;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
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

import hjho.prj.prct.biz.main.model.MenuAuthVO;
import hjho.prj.prct.biz.main.service.MainService;
import hjho.prj.prct.common.exception.AuthVerifyException;
import hjho.prj.prct.common.exception.JwtVerifyException;
import hjho.prj.prct.common.exception.SessionExpirationException;
import hjho.prj.prct.common.util.SessionUtil;
import hjho.prj.prct.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableWebMvc
public class HttpServletInterceptor implements HandlerInterceptor {
	
	private final boolean isHeaderLog = false;
	
	private final boolean isCookieLog = false;
	
	private final boolean isSessionLog = true;
	
	private final boolean isSecurityLog = true;
	
	private final boolean isTestMode = false;
	
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
		
		if(isTestMode) {
			log.info("============= Is Test Mode ================");
		} else {
			// ?????? ?????? ??????. >> ????????? OK or ????????? SET
			stopWatch.start("loginSessionCheck");
			if(mainService.isSessionFail(request)) {
				throw new SessionExpirationException();
			} else {
				// Set User Info
				this.initUserInfo(request);
			}
			stopWatch.stop();
			
			// ?????? ?????? ??? ?????????.
			stopWatch.start("tokenCheck");
			if(mainService.isTokenVerifyFail(request)) {
				throw new JwtVerifyException();
			}
			stopWatch.stop();
			
			// ????????? ?????? ?????? ??????. ??????, ??????, ??????, ?????? authority
			stopWatch.start("authorityCheck");
			/**/
			if(mainService.isMgrAuthorityFail(request)) {
				throw new AuthVerifyException(); // "????????? ????????????."
			}
			stopWatch.stop();
		}
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
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				log.debug("=== {} : {}", StringUtil.RPAD(cookie.getName(), 20, ""), cookie.getValue());
			}
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
	 *      - ????????????(?????? or ??????)??? ????????? ???????????????, isAuthenticated ??? ?????? ?????? ????????? ?????? ??????.
	 * Collection<? extends GrantedAuthority> getAuthorities()
	 *      - ????????? ?????? Principal??? ????????? ?????? ?????????. AuthenticationManager??? ?????? SET ???.
	 * Object getCredentials()
	 *      - ????????? ?????? Principal??? ???????????? ???????????? ???.
	 *      - ??????????????? ??????????????? Authentication ??? ????????? ?????? ?????? ??? ??? ??????.
	 *      - ????????? ???????????? ???????????? ?????? ??????.
	 * Object getDetails() 
	 *      - ??????????????? ??????????????? ????????? ??? ?????? ??????. IP, ????????? ????????? ?????? ?????? ???.
	 * Object getPrincipal()
	 *      - ????????? ????????? ?????????, ??????????????? ????????? ??????.
	 *      - ?????? ?????? ????????????????????? UserDetails ????????? ????????? ???.
	 * boolean isAuthenticated()
	 *      - ????????? ???????????? ?????????????????? ???????????? ?????????.
	 * </pre>
	 * ?????? ???????????? ???????????? UsernamePasswordAuthenticationToken ??? ????
	 * @param request
	 * @return
	 */
	private boolean initUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		// ?????? ??????.
		String requestUri = request.getRequestURI();
		String menuId = "NONE";
		String pageUri = "EMPTY";
		String[] uri = request.getHeader("referer").split(request.getHeader("host"));
		if(uri.length > 1 && uri[1].endsWith("/page")) {
			MenuAuthVO menu = SessionUtil.getUriMenu(session, uri[1]);
			if(ObjectUtils.isNotEmpty(menu)) {
				menuId = menu.getMenuId();
				pageUri = menu.getPageUrl();
			}
		}
		Authentication authentication = new TestingAuthenticationToken(SessionUtil.getUser(session)
				                                                     , SessionUtil.getToken(session)
				                                                     , new String[] {menuId, pageUri, requestUri});

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
		// log.debug("## {}", stopWatch.shortSummary());
		// log.debug("## {}", stopWatch.getTotalTimeMillis());
		log.debug("\n{}", stopWatch.prettyPrint());
		log.info("============= [INTERCEPTOR END] ===================");
	}
}
