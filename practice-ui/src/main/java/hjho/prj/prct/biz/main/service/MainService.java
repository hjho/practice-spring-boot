package hjho.prj.prct.biz.main.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.util.SessionUtil;
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
			log.debug("[V] Session Ok");
		}
		return false;
	}

}
