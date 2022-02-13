package hjho.prj.prct.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
	
	@Autowired
	private MessageSourceAccessor accessor;
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<CommonMessage> handlerUserException(HttpServletRequest request
			                                                , HttpServletResponse response
			                                                , UserException e) {
		// BODY
		CommonMessage message = new CommonMessage();
		message.setCode(e.getCode());
		if(ObjectUtils.isNotEmpty(e.getArgs())) {
			message.setArgs(e.getArgs());
		}
		message.setXmlMessage(accessor);
		
		return this.logResonseEntity(message, request, response, e);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CommonMessage> handlerException(HttpServletRequest request
			                                            , HttpServletResponse response
			                                            , Exception e) {
		// BODY
		CommonMessage message = new CommonMessage();
		message.setError();
		message.setMessage(e.getMessage());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		return this.logResonseEntity(message, request, response, e);
	}
	
	private ResponseEntity<CommonMessage> logResonseEntity(CommonMessage message
														 , HttpServletRequest request
			                                             , HttpServletResponse response
			                                             , Exception e) 
	{
		// HEADER
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		// HTTP STATUS
		HttpStatus status = HttpStatus.valueOf(response.getStatus());
		
		log.error("================== [ EXCEPTION ] ======================");
		for(StackTraceElement element : e.getStackTrace()) {
			if(element.getClassName().indexOf("hjho.prj.prct") > -1) {
				log.debug("=== Cause       : {} ({})", element.getClassName(), element.getLineNumber());
			}
		}
		log.error("=== Exception   : {}", e.getClass());
		log.error("=== Response    : {}", message);
		log.error("=== Http Value  : [{}] {}, {}", status.value(), status.getReasonPhrase(), status.series());
		log.error("=======================================================");
		
		return new ResponseEntity<CommonMessage>(message, headers, status);
	}
	
}
