package hjho.prj.prct.biz.main.service;

import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.model.LoginVO;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;

@Service
public class LoginService extends CommonService {

	public CommonMessage proc(LoginVO paramVO) {
		paramVO.setFunctionYn("Y");
		CommonMessage rspnData = super.post("/api/login/proc", paramVO);
		
		return rspnData;
	}
	
}
