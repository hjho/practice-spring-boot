package hjho.prj.prct.biz.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.main.model.LoginRVO;
import hjho.prj.prct.biz.main.model.MenuAuthPVO;
import hjho.prj.prct.biz.main.model.MenuAuthRVO;
import hjho.prj.prct.biz.main.service.MenuAuthService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/main/menu")
@Api(tags="MainMenuAuth", value="메뉴 권한 조회", description="메뉴 권한 조회")
public class MenuAuthController extends CommonController {

	@Autowired
	private MenuAuthService menuAuthService;
	
	@GetMapping("/auth")
	@ApiOperation(value="getMainMenuAuth", notes="메뉴 권한 조회", response=LoginRVO.class)
	public CommonMessage getMainMenuAuth(@ModelAttribute MenuAuthPVO menuAuthPVO) throws UserException {
		this.parameterLog("MainMenuAuth[getMainMenuAuth]", menuAuthPVO);
		CommonMessage message = new CommonMessage();
		
		List<MenuAuthRVO> returnList = menuAuthService.getMainMenuAuth(menuAuthPVO);
		
		message.setOk();
		message.setData(returnList);
		return message;
	}
	
}
