package hjho.prj.prct.biz.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.main.model.LoginPVO;
import hjho.prj.prct.biz.main.model.LoginRVO;
import hjho.prj.prct.biz.main.service.LoginService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/login")
@Api(tags="Login", value="LOGIN", description="로그인 검증")
public class LoginController extends CommonController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/proc")
	@ApiOperation(value="proc", notes="로그인 검증", response=LoginRVO.class)
	public CommonMessage loginProc(@RequestBody LoginPVO loginPVO) throws UserException {
		this.parameterLog("LoginController.loginProc", loginPVO);
		CommonMessage message = new CommonMessage();
		
		LoginRVO returnVO = loginService.loginProc(loginPVO);
		
		message.setOk();
		message.setData(returnVO);
		return message;
	}
	
}
