package hjho.prj.prct.biz.main.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.model.MenuAuthVO;
import hjho.prj.prct.biz.main.model.MgrInfoVO;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.util.SessionUtil;
import hjho.prj.prct.common.util.VoUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MainService extends CommonService {

	public boolean isSessionFail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(ObjectUtils.isEmpty(SessionUtil.getMgrInfo(session))) {
			log.warn("[V] 마지막 접속이후 {}이 지났습니다.", SessionUtil.getDestroySetTime(session));
			return true;
		} else {
			log.debug("[V] Session And Mgr Ok");
		}
		return false;
	}

	public boolean isTokenVerifyFail(HttpServletRequest request) {
		boolean isFail = true;
		HttpSession session = request.getSession();
		MgrInfoVO mgrInfoVO = SessionUtil.getMgrInfo(session);
		String sessMgrId = mgrInfoVO.getMgrId();
		String token = SessionUtil.getToken(session);
		
		if(StringUtils.isEmpty(token)) {
			log.warn("[V] 토큰이 존재하지 않습니다.");
			return true;
		}
		
		// 1. Access Token Verify.
		CommonMessage atkMsg = super.post("/api/token/verify", token);
		String atkMsgCode = atkMsg.getCode();
		
		// 2. Access Token Verify OK.
		if("0000".equals(atkMsgCode)) {
			Map<String, Object> data = VoUtil.objToMap(atkMsg.getData());
			String tkMgrId = (String) data.get("mgrId");
			
			// 3. Token Data, Login Data Check!
			if(tkMgrId.equals(sessMgrId)) {
				isFail = false;
			}
		// 2. Access Token Verify Fail.
		} else {
			// 4. Access Token Expiration.
			if("9004".equals(atkMsgCode)) {
				
				log.debug("[V] Access Token Expiration");
				// 5. Get Refresh Token Value.
				CommonMessage mgrMsg = super.post("/api/sys/mgr/getToken", sessMgrId);
				
				// 6. Refresh Token Verify.
				CommonMessage rtkMsg = super.post("/api/token/reverify", mgrMsg.getData());
				String rtkMsgCode = rtkMsg.getCode();
				
				// 7. Refresh Token Verify OK.
				CommonMessage tkIssueMsg = null;
				if("0000".equals(rtkMsgCode)) {
					
					// 8. Access Token Reissue. - AccessToken 만 재발급
					tkIssueMsg = super.post("/api/token/reissue", mgrInfoVO);
					
				} else {
					// 9. Refresh Token Expiration.
					if("9004".equals(rtkMsgCode)) {
						
						log.debug("[V] Refresh Token Expiration");
						// 10. Access Token Issue. - AccessToken, RefreshToken 재발급
						tkIssueMsg = super.post("/api/token/issue", mgrInfoVO);
					}
				}
				
				// 11. Access Token Save.
				if("0000".equals(tkIssueMsg.getCode())) {
					SessionUtil.setToken(request, (String) tkIssueMsg.getData());
					isFail = false;
				}
			}
		}
		if(isFail) {
			log.debug("[V] Token Verify Fail");
		} else {
			log.debug("[V] Token Verify Ok");
		}
		return isFail;
	}

	public boolean isMgrAuthorityFail(HttpServletRequest request) {
		CommonMessage message = super.authCheck(request);
		log.debug("[V] Message : {}", message);
		
		MenuAuthVO menu = SessionUtil.getUriMenu(request.getSession(), request.getRequestURI());
		log.debug("[V] Menu Page : {}", menu);
		if(ObjectUtils.isNotEmpty(menu)) {
			if("Y".equals(menu.getAuthYn())) {
				log.debug("[V] Menu Page Verify Ok");
				return false;
			} else {
				log.error("[V] {}의 조회 권한이 없습니다.", menu.getMenuNm());
				return true;
			}
		}
		log.error("[V] {}의 조회 권한이 없습니다.", request.getRequestURI());
		return true;
	}

}
