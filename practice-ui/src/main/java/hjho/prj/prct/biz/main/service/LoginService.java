package hjho.prj.prct.biz.main.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.model.LoginPVO;
import hjho.prj.prct.biz.main.model.MainMenuAuthRVO;
import hjho.prj.prct.biz.main.model.MainMenuAuthVO;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.util.SessionUtil;

@Service
public class LoginService extends CommonService {
	
	private final String LOGIN_API_URL = "/api/login/proc";
	
	private final String MENU_AUTH_API_URL = "/api/main/menu";
	
	// 로그인 검증!
	public CommonMessage proc(LoginPVO paramVO) {
		
		CommonMessage rspnData = super.post(LOGIN_API_URL, paramVO);
		
		return rspnData;
	}

	// 유저 정보 설정!
	public boolean setUser(HttpSession session, Object userData) {
		
		SessionUtil.setUserInfo(session, userData);
		
		return true;
	}
	
	// 메뉴 설정!
	@SuppressWarnings("unchecked")
	public boolean setMenu(HttpSession session, LoginPVO userInfoVO) {
		MainMenuAuthVO userAuthVO = new MainMenuAuthVO();
		userAuthVO.setMgrId(userInfoVO.getUserId());
		
		CommonMessage message = super.get(MENU_AUTH_API_URL, userAuthVO);
		List<MainMenuAuthRVO> authList = (List<MainMenuAuthRVO>) message.getData();
		SessionUtil.setTreeMenu(session, authList);
		
		return true;
	}
	
}
