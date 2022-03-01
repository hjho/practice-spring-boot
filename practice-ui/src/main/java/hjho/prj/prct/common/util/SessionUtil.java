package hjho.prj.prct.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang3.ObjectUtils;

import hjho.prj.prct.biz.main.model.MenuAuthRVO;
import hjho.prj.prct.biz.main.model.MenuAuthVO;
import hjho.prj.prct.biz.main.model.MgrInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionUtil implements HttpSessionListener {
	
	public final static String TREE_MENU = "menu";
	
	public final static String MGR_INFO = "mgr";
	
	public final static String TOKEN = "token";
	
	private static SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		log.debug("[ Session Created   ] ID : {}", se.getSession().getId());
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		log.debug("[ Session Desteoyed ] ID : {}", se.getSession().getId());
	}

	public static boolean logout(HttpSession session) {
		if(ObjectUtils.isEmpty(session)) {
			return false;
		}
		session.invalidate();
		return true;
	}
	
	/***
	 * Session 관리자 정보 저장.
	 * @param userData (관리자)
	 */
	public static void setMgrInfo(HttpServletRequest request, Object MgrInfo) {
		HttpSession session = request.getSession();
		session.removeAttribute(MGR_INFO);
		session.setAttribute(MGR_INFO, MgrInfo);		// user
		log.debug("[ Session Attribute ] MGR  INFO OK, ID : {}", session.getId());
	}
	
	/***
	 * Session에 저장된 관리자 정보.
	 * @return
	 */
	public static MgrInfoVO getMgrInfo(HttpSession session) {
//		log.debug("[ Session getMgrInfo ] MGR ID   : {}",session.getId());
//		log.debug("[ Session getMgrInfo ] MGR INFO : {}",session.getAttribute(SessionUtil.MGR_INFO));
		if(ObjectUtils.isEmpty(session) || ObjectUtils.isEmpty(session.getAttribute(SessionUtil.MGR_INFO))) {
			return null;
		} 
		return (MgrInfoVO) VoUtil.objToVO(session.getAttribute(SessionUtil.MGR_INFO), MgrInfoVO.class);
	}
	
	/***
	 * Session Menu 저장. 
	 * @param userAuthList (메뉴)
	 */
	public static void setTreeMenu(HttpServletRequest request, List<MenuAuthRVO> userAuthList) {
		HttpSession session = request.getSession();
		session.removeAttribute(TREE_MENU);
		session.setAttribute(TREE_MENU, userAuthList);	// menu
		log.debug("[ Session Attribute ] MENU INFO OK, ID : {}", session.getId());
	}
	
	/***
	 * Session Tree Menu 정보. 
	 */
	@SuppressWarnings("unchecked")
	public static List<MenuAuthRVO> getTreeMenu(HttpSession session) {
		if(ObjectUtils.isEmpty(session) || ObjectUtils.isEmpty(session.getAttribute(SessionUtil.TREE_MENU))) {
			return null;
		} 
		// Get Tree Menu
		List<Object> list = (List<Object>) VoUtil.objToVO(session.getAttribute(SessionUtil.TREE_MENU), List.class);
		
		// Type Convert
		List<MenuAuthRVO> returnList = new ArrayList<MenuAuthRVO>();
		for (Object object : list) {
			returnList.add((MenuAuthRVO) VoUtil.objToVO(object, MenuAuthRVO.class));
		}
		
		return returnList;
	}
	
	/***
	 * Session Tree Menu 정보. 
	 */
	@SuppressWarnings("unchecked")
	public static MenuAuthVO getUriMenu(HttpSession session, String uri) {
		if(ObjectUtils.isEmpty(session) || ObjectUtils.isEmpty(session.getAttribute(SessionUtil.TREE_MENU))) {
			return null;
		} 
		// Type Convert
		List<Object> hrList = (List<Object>) VoUtil.objToVO(session.getAttribute(SessionUtil.TREE_MENU), List.class);
		for (Object hrObj : hrList) {
			// 상위 메뉴의 URL이라면.
			MenuAuthRVO menuHr = (MenuAuthRVO) VoUtil.objToVO(hrObj, MenuAuthRVO.class);
			if(uri.startsWith(menuHr.getPageUrl())) {
				
				// Type Convert
				List<Object> lrList = (List<Object>) VoUtil.objToVO(menuHr.getMenuLr(), List.class);
				for (Object lrObj : lrList) {
					// 하위 메뉴 URL 중..
					MenuAuthVO menuLr = (MenuAuthVO) VoUtil.objToVO(lrObj, MenuAuthVO.class);
					if(uri.startsWith(menuLr.getPageUrl())) {
						// OK
						return menuLr;
					}
				}
			}
		}
		
		return null;
	}
	
	/***
	 * Session Token 저장. 
	 * @param Token (메뉴)
	 */
	public static void setToken(HttpServletRequest request, String token) {
		HttpSession session = request.getSession();
		session.removeAttribute(TOKEN);
		session.setAttribute(TOKEN, token);				// token
		log.debug("[ Session Attribute ] TOKEN OK, ID : {}", session.getId());
	}
	
	/***
	 * Session에 저장된 토큰 정보.
	 * @return
	 */
	public static String getToken(HttpSession session) {
		if(ObjectUtils.isEmpty(session) || ObjectUtils.isEmpty(session.getAttribute(SessionUtil.TOKEN))) {
			return null;
		} 
		return (String) session.getAttribute(SessionUtil.TOKEN);
	}
	
	/***
	 * Session 생성 시간.
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCreationTime(HttpSession session) {
		if(ObjectUtils.isEmpty(session)) {
			return "";
		}
		Date date = new Date(session.getCreationTime());
		return simpleDate.format(date);
	}
	
	/***
	 * Session 소멸 예정 시간.
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getDestroyTime(HttpSession session) {
		if(ObjectUtils.isEmpty(session)) {
			return "";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date()); 
		cal.add(Calendar.SECOND, session.getMaxInactiveInterval());
		
		return simpleDate.format(cal.getTime());
	}
	
	/***
	 * Session 소멸 설정 시간.
	 * @return OO분
	 */
	public static String getDestroySetTime(HttpSession session) {
		if(ObjectUtils.isEmpty(session)) {
			return "";
		}
		
		int minute =  session.getMaxInactiveInterval() / 60;
		
		return Integer.toString(minute).concat("분");
	}
	
	/***
	 * Session 최근 접근 시간.
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getLastAccessedTime(HttpSession session) {
		if(ObjectUtils.isEmpty(session)) {
			return "";
		}
		Date date = new Date(session.getLastAccessedTime());
		return simpleDate.format(date);
	}
	
	/***
	 * Session 소멸 시간 연장.(10분)
	 * @return 
	 */
	public static boolean addDestroyTime(HttpSession session) {
		if(ObjectUtils.isEmpty(session)) {
			return false;
		}
		int max = session.getMaxInactiveInterval();
		session.setMaxInactiveInterval(max + (600));
		return true;
	}
	
}
