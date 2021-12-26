package hjho.prj.prct.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class WebExceptionHandler {
	
	@ExceptionHandler(UserException.class)
	public ModelAndView handlerUserException(HttpServletRequest request, HttpServletResponse response, UserException e) {
		
//		Enumeration<String> headers = request.getHeaderNames();
//		while(headers.hasMoreElements()) {
//			String headerName = headers.nextElement();
//			log.debug("#### [ handlerUserException Header    : {}[{}]", headerName, request.getHeader(headerName));
//		}
		log.debug("[H] UserException Class   : {}", e.getClass());
		log.debug("[H] UserException Message : {}", e.getMessage());
		
		String header = request.getHeader("user-agent");
		
		ModelAndView mav = null;
		// message.setRspn(e.getCode());
		CommonMessage message = new CommonMessage();
		if(header != null && header.indexOf("HttpClient") > -1) {
			mav = new ModelAndView("jsonView");
			mav.addObject("code"   , message.getCode());
			mav.addObject("message", message.getMessage());
			
		}
		
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		
//		Enumeration<String> headers = request.getHeaderNames();
//		while(headers.hasMoreElements()) {
//			String headerName = headers.nextElement();
//			log.debug("#### [ handlerException Header    : {}[{}]", headerName, request.getHeader(headerName));
//		}
		log.debug("[H] Exception Class   : {}", e.getClass());
		log.debug("[H] Exception Message : {}", e.getMessage());
		
		return this.getErrorModelAndView(request, e);
	}
	
	// HTML ERROR RETURN
	private ModelAndView getErrorModelAndView(HttpServletRequest request, Exception e) {
		
		String header = request.getHeader("user-agent");
		CommonMessage message = new CommonMessage();
		message.setError();
		
		ModelAndView mav = null;
		if(header != null && header.indexOf("HttpClient") > -1) {
			mav = new ModelAndView("jsonView");
			mav.addObject("code"   , message.getCode());
			mav.addObject("message", message.getMessage());
			
		}
		
		return mav;
	}
}
