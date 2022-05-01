package hjho.prj.prct.common.util;

import java.util.HashMap;
import java.util.Map;

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
	
	public static Map<String, String> getAuthoritie() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Object[] obj = authentication.getAuthorities().toArray();
		Map<String, String> map = new HashMap<String, String>();
		map.put("menuId"    , obj[0].toString());
		map.put("pageUri"   , obj[1].toString());
		map.put("requestUri", obj[2].toString());
		return map;
	}
}
