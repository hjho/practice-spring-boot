package hjho.prj.prct.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import hjho.prj.prct.biz.main.model.MgrInfoVO;

public class SecurityUtil {
	
	public static String getToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (String) authentication.getCredentials();
	}
	
	public static MgrInfoVO getMgrInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (MgrInfoVO) VoUtil.objToVO(authentication.getPrincipal(), MgrInfoVO.class);
	}
}
