package hjho.prj.prct.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang3.ObjectUtils;

import hjho.prj.prct.biz.main.model.MainMenuAuthRVO;
import hjho.prj.prct.biz.main.model.MgrInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionUtil implements HttpSessionListener {
	
	public final static String TREE_MENU = "menu";
	
	public final static String MGR_INFO = "mgr";
	
	private static HttpSession session;
	
	private static SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		log.debug("[ Session Created   ] ID : {}", se.getSession().getId());
		session = se.getSession();
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		log.debug("[ Session Desteoyed ] ID : {}", se.getSession().getId());
	}

	public static boolean logout() {
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
		session = request.getSession();
		session.removeAttribute(MGR_INFO);
		session.setAttribute(MGR_INFO, MgrInfo);		// user
		log.debug("[ >>>>> Session Set Mgr Ok ]");
	}
	
	/***
	 * Session Menu 저장. 
	 * @param userAuthList (메뉴)
	 */
	public static void setTreeMenu(HttpServletRequest request, List<MainMenuAuthRVO> userAuthList) {
		session = request.getSession();
		session.removeAttribute(TREE_MENU);
		session.setAttribute(TREE_MENU, userAuthList);	// menu
		log.debug("[ >>>>> Session Set Menu Ok ]");
	}
	
	/***
	 * Session에 저장된 관리자 정보.
	 * @return
	 */
	public static MgrInfoVO getMgrInfo() {
		if(ObjectUtils.isEmpty(session) || ObjectUtils.isEmpty(session.getAttribute(SessionUtil.MGR_INFO))) {
			return null;
		} 
		return (MgrInfoVO) VoUtil.objToVO(session.getAttribute(SessionUtil.MGR_INFO), MgrInfoVO.class);
	}
	
	/***
	 * Session 생성 시간.
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCreationTime() {
		if(ObjectUtils.isEmpty(session)) {
			return "";
		}
		Date date = new Date(session.getCreationTime());
		return simpleDate.format(date);
	}
	
	/***
	 * Session 소멸 시간.
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getDestroyTime() {
		if(ObjectUtils.isEmpty(session)) {
			return "";
		}
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(new Date(session.getCreationTime())); 
		cal.add(Calendar.SECOND, session.getMaxInactiveInterval());
		
		return simpleDate.format(cal.getTime());
	}
	
	/***
	 * Session 소멸 시간 연장.(10분)
	 * @return 
	 */
	public static boolean addDestroyTime() {
		if(ObjectUtils.isEmpty(session)) {
			return false;
		}
		int max = session.getMaxInactiveInterval();
		session.setMaxInactiveInterval(max + (600));
		return true;
	}
	
}
