package hjho.prj.prct.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
		log.info("[H] [BEFORE ] Http Servlet Interceptor : {}", request.getRequestURL().toString());
		StopWatch stopWatch = new StopWatch(request.getRequestURL().toString());
		stopWatch.start("ResponseTime");
		request.setAttribute(STOP_WATCH, stopWatch);
		
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
		HttpSession session = request.getSession();
		log.debug("[H] [BEFORE ] Http Session Id : {}", session.getId());
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			               @Nullable ModelAndView modelAndView)
	throws Exception 
	{
		log.info("[H] [AFTER  ] Http Servlet Interceptor");
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
		log.info("[H] [COMPLET] Http Servlet Interceptor");
		
	}
	
}
