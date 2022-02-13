package hjho.prj.prct.biz.main.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.model.LoginPVO;
import hjho.prj.prct.biz.main.model.MainMenuAuthRVO;
import hjho.prj.prct.biz.main.model.MainMenuAuthVO;
import hjho.prj.prct.biz.main.model.MgrInfoVO;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.util.SessionUtil;

@Service
public class LoginService extends CommonService {
	
	private final String LOGIN_API_URL = "/api/login/proc";
	
	private final String MENU_AUTH_API_URL = "/api/main/menu";
	
	private final String TOKEN_API_URL = "/api/token/issue";
	
	// 로그인 검증!
	public CommonMessage proc(LoginPVO paramVO) {
		
		CommonMessage rspnData = super.post(LOGIN_API_URL, paramVO);
		
		return rspnData;
	}

	// 유저 정보 설정!
	public boolean setUser(HttpServletRequest request, MgrInfoVO mgrInfoVO) {
		
		SessionUtil.setMgrInfo(request, mgrInfoVO);
		
		return true;
	}
	
	// 메뉴 설정!
	@SuppressWarnings("unchecked")
	public boolean setMenu(HttpServletRequest request, MgrInfoVO mgrInfoVO) {
		MainMenuAuthVO userAuthVO = new MainMenuAuthVO();
		userAuthVO.setMgrId(mgrInfoVO.getMgrId());
		
		CommonMessage message = super.get(MENU_AUTH_API_URL, userAuthVO);
		List<MainMenuAuthRVO> authList = (List<MainMenuAuthRVO>) message.getData();
		
		SessionUtil.setTreeMenu(request, authList);
		
		return true;
	}

	public boolean setToken(HttpServletRequest request, MgrInfoVO mgrInfoVO) {
		
		CommonMessage message = super.post(TOKEN_API_URL, mgrInfoVO);
		
		SessionUtil.setToken(request, (String) message.getData());
		
		return true;
	}
	
	// 로그아웃!
	public boolean logout(HttpServletRequest request) {
		
		boolean isLogout = SessionUtil.logout(request.getSession());
		
		return isLogout;
	}

	
}
