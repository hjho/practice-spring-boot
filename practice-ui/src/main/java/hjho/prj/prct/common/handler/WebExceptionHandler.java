package hjho.prj.prct.common.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.common.exception.AuthVerifyException;
import hjho.prj.prct.common.exception.SessionExpirationException;
import hjho.prj.prct.common.exception.UserException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class WebExceptionHandler {
	
	@ExceptionHandler(UserException.class)
	public ModelAndView handlerUserException(HttpServletRequest request, HttpServletResponse response, UserException e) {
		
		this.errorMessage(e);
		
		ModelAndView mav = new ModelAndView();
		
		if(this.isRequestAjax(request)) {
			mav.setViewName("jsonView");
			mav.addObject("code"   , e.getCode());
			mav.addObject("message", e.getMessage());
			mav.setStatus(HttpStatus.OK);
		}
		
		return mav;
	}
	
	@ExceptionHandler(AuthVerifyException.class)
	public ModelAndView handlerAuthVerifyException(HttpServletRequest request, HttpServletResponse response, AuthVerifyException e) {
		
		this.errorMessage(e);
		
		ModelAndView mav = new ModelAndView();
		
		if(this.isRequestAjax(request)) {
			mav.setViewName("jsonView");
		} else {
			mav.setViewName("/error/authVerify");
		}
		
		return this.returnMessageMapping(request, mav, e.getStatus(), e.getMessage());
	}
	
	@ExceptionHandler(SessionExpirationException.class)
	public ModelAndView handlerSessionExpirationException(HttpServletRequest request, HttpServletResponse response, SessionExpirationException e) {
		
		this.errorMessage(e);
		
		ModelAndView mav = new ModelAndView();
		
		if(this.isRequestAjax(request)) {
			mav.setViewName("jsonView");
		} else {
			mav.setViewName("/error/sessionExpiration");
		}
		
		return this.returnMessageMapping(request, mav, e.getStatus(), e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		
		this.errorMessage(e);
		
		ModelAndView mav = new ModelAndView();
		
		if(this.isRequestAjax(request)) {
			mav.setViewName("jsonView");
		} else {
			mav.setViewName("/error/5xx");
		}
		
		return this.returnMessageMapping(request, mav, HttpStatus.INTERNAL_SERVER_ERROR, "일시적인 오류가 발생했습니다.");
	}

	private void errorMessage(Exception e) {
		log.error("================== [ EXCEPTION ] ======================");
		log.error("=== Class    : {}", e.getClass());
		log.error("=== Message  : {}", e.getMessage());
		for(StackTraceElement element : e.getStackTrace()) {
			if(element.getClassName().startsWith("hjho.prj.prct")) {
				log.error("=== Cause    : {} ({})", element.getClassName(), element.getLineNumber());
			}
		}
		log.error("=======================================================");
	}
	
	private boolean isRequestAjax(HttpServletRequest request) {
		String ajaxHeaderName = "x-requested-with";
		String ajaxHeader     = "XMLHttpRequest";
		String header = request.getHeader(ajaxHeaderName);
		if(header != null && ajaxHeader.equals(header)) {
			return true;
		}
		return false;
	}
	
	private ModelAndView returnMessageMapping(HttpServletRequest request, ModelAndView mav, HttpStatus status, String message) {
		
		mav.setStatus(status);
		mav.addObject("message", message);
		mav.addObject("path", request.getRequestURI());
		mav.addObject("error", status.getReasonPhrase());
		mav.addObject("status", status.value());
		mav.addObject("timestamp", new Date());
		
		return mav;
	}
	
}
