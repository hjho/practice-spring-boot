package hjho.prj.prct.common.filter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParameterResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		log.debug("[F] Parameter Resolver : {}", parameter.getParameterType());
		return false; // methodParameter.getParameterType().equals(HashMap.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter
			                    , ModelAndViewContainer mavContainer
			                    , NativeWebRequest webRequest
			                    , WebDataBinderFactory binderFactory)
    throws Exception 
	{
		String 이런느낌 = webRequest.getParameter("이런느낌");
		Map<String, String> map = new HashMap<String, String>();
		map.put("이런느낌", 이런느낌);
		return null; // map
	}

}
