package hjho.prj.prct.common.filter;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import hjho.prj.prct.common.clazz.CommonModel;
import hjho.prj.prct.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParameterResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		this.isCommonModel(parameter.getParameterType());
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter
			                    , ModelAndViewContainer mavContainer
			                    , NativeWebRequest webRequest
			                    , WebDataBinderFactory binderFactory)
    throws Exception 
	{
		// TODO
		String[] menuId = new String[] {SecurityUtil.getAuthoritie().get("menuId")};
		String[] mgrId  = new String[] {SecurityUtil.getMgrInfo().getMgrId()};
		webRequest.setAttribute("cretSysId", menuId[0], 0);
		webRequest.setAttribute("updSysId", mgrId[0], 0);
		webRequest.setAttribute("cretMgrId", mgrId[0], 0);
		webRequest.setAttribute("updMgrId", mgrId[0], 0);
		return parameter;
	}
	
	private boolean isCommonModel(Class<?> paramClass) {
		log.debug("[F] Parameter Resolver Class : {}", paramClass);
		if(Object.class.equals(paramClass)) {
			return false;
		}
		
		if(CommonModel.class.equals(paramClass)) {
			return true;
		}
		
		return this.isCommonModel(paramClass.getSuperclass());
	}

}
