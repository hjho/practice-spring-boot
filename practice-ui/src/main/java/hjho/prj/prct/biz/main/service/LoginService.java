package hjho.prj.prct.biz.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.model.LoginPVO;
import hjho.prj.prct.biz.main.model.MenuAuthPVO;
import hjho.prj.prct.biz.main.model.MenuAuthRVO;
import hjho.prj.prct.biz.main.model.MgrInfoVO;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;
import hjho.prj.prct.common.util.SessionUtil;

@Service
public class LoginService extends CommonService {
	
	// 로그인 검증!
	public CommonMessage proc(LoginPVO paramVO) {
		
		CommonMessage rspnData = super.post(URI.MAIN_LOGIN_PROC_API, paramVO);
		
		return rspnData;
	}

	// 관리자 정보 설정!
	public boolean setMgr(HttpServletRequest request, MgrInfoVO mgrInfoVO) {
		
		SessionUtil.setMgrInfo(request, mgrInfoVO);
		
		return true;
	}
	
	// 유저 정보 설정!
	public boolean setUser(HttpServletRequest request, MgrInfoVO mgrInfoVO) {
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("mgrId", mgrInfoVO.getMgrId());
		userMap.put("mgrGrpId", mgrInfoVO.getMgrGrpId());
		SessionUtil.setUser(request, userMap);
		
		return true;
	}
	
	// 메뉴 설정!
	@SuppressWarnings("unchecked")
	public boolean setMenu(HttpServletRequest request, MgrInfoVO mgrInfoVO) {
		MenuAuthPVO input = new MenuAuthPVO();
		input.setMgrGrpId(mgrInfoVO.getMgrGrpId());
		
		CommonMessage message = super.get(URI.MAIN_MENU_AUTH_API, input);
		List<MenuAuthRVO> authList = (List<MenuAuthRVO>) message.getData();
		
		SessionUtil.setTreeMenu(request, authList);
		
		return true;
	}

	// 토큰 설정!
	public boolean setToken(HttpServletRequest request, MgrInfoVO mgrInfoVO) {
		
		CommonMessage message = super.post(URI.MAIN_TOKEN_ISSUE_API, mgrInfoVO);
		
		SessionUtil.setToken(request, (String) message.getData());
		
		return true;
	}
	
	// 로그아웃!
	public boolean logout(HttpServletRequest request) {
		
		boolean isLogout = SessionUtil.logout(request.getSession());
		
		return isLogout;
	}

	
}
