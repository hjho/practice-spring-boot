package hjho.prj.prct.biz.main.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.model.MgrInfoVO;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;
import hjho.prj.prct.common.util.SessionUtil;
import hjho.prj.prct.common.util.StringUtil;
import hjho.prj.prct.common.util.VoUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MainService extends CommonService {

	public boolean isSessionFail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(ObjectUtils.isEmpty(SessionUtil.getUser(session))) {
			log.warn("[V] 마지막 접속이후 {}이 지났습니다.", SessionUtil.getDestroySetTime(session));
			return true;
		} else {
			log.debug("[V] Verify Session Ok");
		}
		return false;
	}

	public boolean isTokenVerifyFail(HttpServletRequest request) {
		boolean isFail = true;
		HttpSession session = request.getSession();
		MgrInfoVO mgrInfoVO = SessionUtil.getUser(session);
		String sessMgrId = mgrInfoVO.getMgrId();
		
		String token = SessionUtil.getToken(session);
		
		if(StringUtils.isEmpty(token)) {
			log.warn("[V] Json Web Token Empty.");
			return true;
		}
		
		// 1. Access Token Verify.
		Map<String, String> tokenMap = new HashMap<String, String>();
		tokenMap.put("token", token);
		CommonMessage atkMsg = super.post(URI.MAIN_TOKEN_VERIFY_API, tokenMap);
		
		// 2. Access Token Verify OK.
		if(atkMsg.isSuccess()) {
			Map<String, Object> data = VoUtil.objToMap(atkMsg.getData());
			String tkMgrId = (String) data.get("mgrId");
			
			// 3. Token Data, Login Data Check!
			if(sessMgrId.equals(tkMgrId)) {
				isFail = false;
			}
		// 2. Access Token Verify Fail.
		} else {
			// 4. Access Token Expiration.
			if("9004".equals(atkMsg.getCode())) {
				
				log.debug("[V] Access Json Web Token Expiration");
				// 5. Get Refresh Token Value.
				CommonMessage mgrMsg = super.post(URI.MAIN_REFRESH_TOKEN_API, mgrInfoVO);
				
				// 6. Refresh Token Verify.
				Map<String, String> refreshTokenMap = new HashMap<String, String>();
				refreshTokenMap.put("refreshToken", (String) mgrMsg.getData());
				CommonMessage rtkMsg = super.post(URI.MAIN_TOKEN_REVERIFY_API, refreshTokenMap);
				
				// 7. Refresh Token Verify OK.
				CommonMessage tkIssueMsg = null;
				if(rtkMsg.isSuccess()) {
					
					// 8. Access Token Reissue. - AccessToken 만 재발급
					tkIssueMsg = super.post(URI.MAIN_TOKEN_REISSUE_API, mgrInfoVO);
					
				} else {
					// 9. Refresh Token Expiration.
					if("9004".equals(rtkMsg.getCode())) {
						
						log.debug("[V] Refresh Json Web Token Expiration");
						// 10. Access Token Issue. - AccessToken, RefreshToken 재발급
						tkIssueMsg = super.post(URI.MAIN_TOKEN_ISSUE_API, mgrInfoVO);
					}
				}
				
				// 11. Access Token Save.
				if(tkIssueMsg.isSuccess()) {
					SessionUtil.setToken(request, (String) tkIssueMsg.getData());
					isFail = false;
				}
			}
		}
		if(isFail) {
			log.warn("[V] Verify Json Web Token Fail");
		} else {
			log.debug("[V] Verify Json Web Token Ok");
		}
		return isFail;
	}

	public boolean isMgrAuthorityFail(HttpServletRequest request) {
		boolean isFail = true;
		
		CommonMessage message = super.authCheck(request);
		
		if(message.isSuccess()) {
			String yn = (String) message.getData();
			if(StringUtil.isY(yn)) {
				isFail = false;
			}
		}
		if(isFail) {
			log.warn("[V] Verify Mgr Auth Fail : {}", message.getMessage());
		} else {
			log.debug("[V] Verify Mgr Auth Ok");
		}
		return isFail;
	}

}
