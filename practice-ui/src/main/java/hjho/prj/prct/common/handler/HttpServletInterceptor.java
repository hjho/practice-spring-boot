package hjho.prj.prct.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
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

import hjho.prj.prct.common.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableWebMvc
public class HttpServletInterceptor implements HandlerInterceptor {
	
	private final String STOP_WATCH = "stopWatch";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	throws Exception 
	{
		log.info("[H] [ Http BEFORE  ] : {}", request.getRequestURL().toString());
		StopWatch stopWatch = new StopWatch(request.getRequestURL().toString());
		stopWatch.start("ResponseTime");
		request.setAttribute(STOP_WATCH, stopWatch);
		this.initUserInfo(request);
		
//		// 로그인 여부 확인
//		StopWatch loginStopWatch = new StopWatch("LoginCheck");
//		loginStopWatch.start();
//		loginStopWatch.stop();
//		log.info("\n{}", loginStopWatch.shortSummary());
//		
//		// 토큰 확인?
//		StopWatch tokenStopWatch = new StopWatch("TokenCheck");
//		tokenStopWatch.start();
//		tokenStopWatch.stop();
//		log.info("\n{}", tokenStopWatch.shortSummary());
//		
//		// 사용자 역할 권한 체크
//		StopWatch ruleStopWatch = new StopWatch("RuleCheck");
//		ruleStopWatch.start();
//		ruleStopWatch.stop();
//		log.info("\n{}", ruleStopWatch.shortSummary());
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			               @Nullable ModelAndView modelAndView)
	throws Exception 
	{
		log.info("[H] [ Http AFTER   ] : {}", request.getRequestURL().toString());
		this.delUserInfo();
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			                    @Nullable Exception ex) 
	throws Exception 
	{
		StopWatch stopWatch = (StopWatch) request.getAttribute(STOP_WATCH);
		stopWatch.stop();
		// log.info("## {}", stopWatch.shortSummary());
		// log.info("## {}", stopWatch.getTotalTimeMillis());
		// log.debug("\n{}", stopWatch.prettyPrint());
		log.info("[H] [ Http COMPLET ] : {}", request.getRequestURL().toString());
	}
	
	private boolean initUserInfo(HttpServletRequest request) {
		String sessionId = request.getSession().getId();
		log.debug("[H] [ Security     ] Session       : {}", sessionId);
		
		if(ObjectUtils.isEmpty(SessionUtil.getMgrInfo())) {
			return false;
		}
		
		Authentication authentication = new TestingAuthenticationToken(SessionUtil.getMgrInfo(), sessionId, new String[] {"CRET", "READ", "UPD", "DEL"}); 
		SecurityContext context = SecurityContextHolder.createEmptyContext(); 
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context); 
		authentication.isAuthenticated();
		log.debug("[H] [ Security     ] Authenticated : {}", authentication.isAuthenticated());
		log.debug("[H] [ Security     ] Principal     : {}", authentication.getPrincipal());
		log.debug("[H] [ Security     ] Credentials   : {}", authentication.getCredentials());
		log.debug("[H] [ Security     ] Authorities   : {}", authentication.getAuthorities());
		
		return true;
	}
	
	private void delUserInfo() {
		SecurityContextHolder.clearContext();
	}
	
}
