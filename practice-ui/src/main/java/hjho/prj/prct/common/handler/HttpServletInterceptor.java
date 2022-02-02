package hjho.prj.prct.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	/**
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
		String sessionId = session.getId();
		log.debug("[H] [ Security     ] Session       : {}", sessionId);
		
		if(ObjectUtils.isEmpty(SessionUtil.getMgrInfo(session))) {
			log.warn("[H] [ Security     ] Mgr Info Null");
			return false;
		}
		
		Authentication authentication = new TestingAuthenticationToken(SessionUtil.getMgrInfo(session), sessionId, new String[] {"CRET", "READ", "UPD", "DEL"});
		SecurityContext context = SecurityContextHolder.createEmptyContext(); 
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context); 
		
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
