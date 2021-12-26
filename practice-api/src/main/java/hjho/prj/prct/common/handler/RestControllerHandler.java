package hjho.prj.prct.common.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import hjho.prj.prct.common.clazz.CommonMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestControllerHandler implements ResponseBodyAdvice<Object> {
	
	@Autowired
	private MessageSourceAccessor accessor;
	
	@Override
	public boolean supports(MethodParameter returnType
						  , Class<? extends HttpMessageConverter<?>> converterType) {
		
		// log.debug("#### [ Rest Controller Handler supports ] ####");
		// log.debug("#### [ Response Method Name : [{}]", returnType.getMethod().getName());
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body
								, MethodParameter returnType
								, MediaType selectedContentType
								, Class<? extends HttpMessageConverter<?>> selectedConverterType
								, ServerHttpRequest request
								, ServerHttpResponse response) 
	{
		CommonMessage message = new CommonMessage();
		if(body instanceof CommonMessage) {
			message = (CommonMessage) body;
		} else {
			return body;
		}
		message.setXmlMessage(accessor);
		
		if(returnType.getMethodAnnotation(ExceptionHandler.class) == null) {
			log.debug("============= [Response] ===============");
			log.debug("=== Method Name : {}", returnType.getMethod().getName());
			log.debug("=== Code        : ({}) {}", message.getCode(), message.getMessage());
			log.debug("=== Data        : {}", message.getData());
			log.debug("========================================");
		}
		
		return message;
	}
	
}
