package hjho.prj.prct.biz.main.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.main.model.MenuAuthPVO;
import hjho.prj.prct.biz.main.model.MenuAuthRVO;
import hjho.prj.prct.biz.main.model.MgrAuthPVO;
import hjho.prj.prct.biz.main.model.MgrAuthRVO;
import hjho.prj.prct.biz.main.service.MenuAuthService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/main/auth")
@Api(tags="MenuAuth", value="메뉴 권한 조회", description="메뉴 권한 조회")
public class MenuAuthController extends CommonController {

	@Autowired
	private MenuAuthService menuAuthService;
	
	@GetMapping("/menu")
	@ApiOperation(value="getMenuAuth", notes="메뉴 권한 조회", response=MenuAuthRVO.class)
	public CommonMessage getMenuAuth(@ModelAttribute MenuAuthPVO menuAuthPVO) throws UserException {
		this.parameterLog("MenuAuth[getMenuAuth]", menuAuthPVO);
		CommonMessage message = new CommonMessage();
		
		List<MenuAuthRVO> returnList = menuAuthService.getMainMenuAuth(menuAuthPVO);
		
		message.setOk();
		message.setData(returnList);
		return message;
	}
	
	@PostMapping("/mgr")
	@ApiOperation(value="getMgrAuth", notes="메뉴 권한 조회", response=MenuAuthRVO.class)
	public CommonMessage getMgrAuth(@RequestHeader(name = "Authorization") String token
			                      , @RequestBody Map<String, Object> authMap) throws UserException {
		
		this.parameterLog("MenuAuth[getMgrAuth]", authMap);
		this.parameterLog("MenuAuth[getMgrAuth]", token);
		CommonMessage message = new CommonMessage();
		// /company/employees/job
		// /page
		// get
		// 
		MgrAuthRVO response = null; //menuAuthService.getMgrAuth(mgrAuthPVO);
		
		message.setOk();
		message.setData(response);
		return message;
	}
	
}
