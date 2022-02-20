package hjho.prj.prct.biz.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.main.model.MgrInfoVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.JsonWebTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/main/token")
@Api(tags="Token", value="TOKEN", description="토큰 발급 및 검증")
public class TokenController extends CommonController {

	@Autowired
	private JsonWebTokenUtils jsonWebTokenUtils;
	
//	@Autowired
//	private SysMgrMgService sysMgrMgService;
	
	@PostMapping("/issue")
	@ApiOperation(value="issue", notes="토큰 발급", response=String.class)
	public CommonMessage issue(@RequestBody MgrInfoVO mgrInfoVO) throws UserException {
		this.parameterLog("TokenController[issue]", mgrInfoVO);
		CommonMessage message = new CommonMessage();
		// AccessToken Input Set
		Map<String, Object> inputTK = new HashMap<String, Object>();
		inputTK.put("mgrId", mgrInfoVO.getMgrId());
		inputTK.put("mgrGrpId", mgrInfoVO.getMgrGrpId());
		
		// Access Token Issue
		String accessToken = jsonWebTokenUtils.createJWT(inputTK);
		
		// Refresh Token Issue
		String refreshToken = jsonWebTokenUtils.createRefreshJWT();
		
		// Refresh Token Value Save
		/*
		if(sysMgrMgService.tokenSave(mgrInfoVO, refreshToken)) {
			log.debug("[TOKEN] 발급 및 저장 완료 : {}, {}", mgrInfoVO.getMgrId(), mgrInfoVO.getMgrGrpId());
		}*/
		
		message.setOk();
		message.setData(accessToken);
		return message;
	}
	
	@PostMapping("/reissue")
	@ApiOperation(value="reissue", notes="토큰 발급", response=String.class)
	public CommonMessage reissue(@RequestBody MgrInfoVO mgrInfoVO) throws UserException {
		this.parameterLog("TokenController[reissue]", mgrInfoVO);
		CommonMessage message = new CommonMessage();
		
		// AccessToken Input Set
		Map<String, Object> inputTK = new HashMap<String, Object>();
		inputTK.put("mgrId", mgrInfoVO.getMgrId());
		inputTK.put("mgrGrpId", mgrInfoVO.getMgrGrpId());
		
		// Access Token Issue
		String accessToken = jsonWebTokenUtils.createJWT(inputTK);
		
		log.debug("[TOKEN] 발급 완료 : {}, {}", mgrInfoVO.getMgrId(), mgrInfoVO.getMgrGrpId());
		
		message.setOk();
		message.setData(accessToken);
		return message;
	}
	
	@PostMapping("/verify")
	@ApiOperation(value="verify", notes="토큰 검증", response=String.class)
	public CommonMessage verify(@RequestBody String token) throws UserException {
		this.parameterLog("TokenController[verify]", token);
		CommonMessage message = new CommonMessage();
		Object data = jsonWebTokenUtils.verifyJWT(token);
		message.setOk();
		message.setData(data);
		return message;
	}
	
	@PostMapping("/reverify")
	@ApiOperation(value="reverify", notes="리프레쉬 토큰 검증", response=Boolean.class)
	public CommonMessage reverify(@RequestBody String token) throws UserException {
		this.parameterLog("TokenController[reverify]", token);
		CommonMessage message = new CommonMessage();
		boolean isOk = jsonWebTokenUtils.verifyRefreshJWT(token);
		message.setOk();
		message.setData(isOk);
		return message;
	}
	
}
