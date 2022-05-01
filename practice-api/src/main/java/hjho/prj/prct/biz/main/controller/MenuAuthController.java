package hjho.prj.prct.biz.main.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.main.model.MenuAuthPVO;
import hjho.prj.prct.biz.main.model.MenuAuthRVO;
import hjho.prj.prct.biz.main.model.MgrAuthPVO;
import hjho.prj.prct.biz.main.model.MgrAuthRVO;
import hjho.prj.prct.biz.main.service.MenuAuthService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.StringUtil;
import io.micrometer.core.instrument.util.StringUtils;
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
	@ApiOperation(value="getMgrAuth", notes="메뉴 권한 조회", response=String.class)
	public CommonMessage getMgrAuth(@RequestHeader(name = "Authorization") String token
			                      , @RequestBody MgrAuthPVO mgrAuthPVO) throws UserException {
		
		this.parameterLog("MenuAuth[getMgrAuth]", mgrAuthPVO);
		CommonMessage message = new CommonMessage();
		
		MgrAuthRVO response = menuAuthService.getMgrAuth(mgrAuthPVO);
		
		// 메소드 권한 확인.
		String menuNm = "";
		String type = "";
		String authYn = "N";
		if(ObjectUtils.isNotEmpty(response)) {
			String authType = mgrAuthPVO.getMethod();
			menuNm = response.getMenuNm();
			switch(authType) {
				case CRET: if(StringUtil.isY(response.getCretAuthYn())) authYn = "Y";
					type = "등록";
					break;
				case READ: if(StringUtil.isY(response.getReadAuthYn())) authYn = "Y";
					type = "조회";
					break;
				case UPD:  if(StringUtil.isY(response.getUpdAuthYn())) authYn = "Y";
					type = "수정";
					break;
				case DEL:  if(StringUtil.isY(response.getDelAuthYn())) authYn = "Y";
					type = "삭제";
					break;
				case PRIV: if(StringUtil.isY(response.getPrivDataReadAuthYn())) authYn = "Y";
					type = "개인정보조회";
					break;
				case EXPT: if(StringUtil.isY(response.getExptAuthYn())) authYn = "Y";
					type = "출력";
					break;
				default:
					break;
			}
		}
		
		// "Y"일 결우.
		if(StringUtil.isY(authYn)) {
			message.setOk();
			
		// "N"일 경우
		} else {
			// 해당 메뉴가 있고, 권한유형이 올바를 경우.
			if(StringUtils.isNotEmpty(menuNm) && StringUtils.isNotEmpty(type)) {
				message.setCode("9998");
				message.setArgs(new String[] {menuNm, type});
				
			// 해당 메뉴가 없거나 권한유형이 올바르지 않을 경우.
			} else {
				message.setCode("9997");
			}
		}
		
		message.setData(authYn);
		return message;
	}
	
}
