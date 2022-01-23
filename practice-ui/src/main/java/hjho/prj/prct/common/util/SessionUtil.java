package hjho.prj.prct.common.util;

import java.util.List;

import javax.servlet.http.HttpSession;

import hjho.prj.prct.biz.main.model.MainMenuAuthRVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionUtil {
	
	private final static String TREE_MENU = "menu";
	
	private final static String USER_INFO = "user";
	
	public static void setUserInfo(HttpSession session, Object userData) {
		
		log.debug("########## Set User Info 1 : {}", userData);
		
		session.removeAttribute(USER_INFO);
		
		session.setAttribute(USER_INFO, userData);		// user
		
		session.setMaxInactiveInterval(60 * 30);		// 30 분
		
		log.debug("########## Set User Info 2 : {}", session.getAttribute(USER_INFO));
		
	}
	
	public static void setTreeMenu(HttpSession session, List<MainMenuAuthRVO> userAuthList) {
		
		log.debug("########## Set Tree Menu 1 : {}", userAuthList);
		
		session.removeAttribute(TREE_MENU);
		
		session.setAttribute(TREE_MENU, userAuthList);	// menu
			
		session.setMaxInactiveInterval(60 * 30);		// 30 분
		
		log.debug("########## Set Tree Menu 2 : {}", session.getAttribute(TREE_MENU));
		
	}
	
}
