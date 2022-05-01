package hjho.prj.prct.biz.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.main.model.LoginRVO;
import hjho.prj.prct.biz.system.model.MenuMgPagingPVO;
import hjho.prj.prct.biz.system.model.MenuMgPagingRVO;
import hjho.prj.prct.biz.system.model.MenuMgVO;
import hjho.prj.prct.biz.system.service.MenuMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/system/menu")
@Api(tags="SysMenuMg", value="시스템 메뉴 관리", description="시스템 메뉴 관리")
public class MenuMgController extends CommonController {
	
	@Autowired
	private MenuMgService menuMgService;
	
	@GetMapping()
	@ApiOperation(value="getSysMenu", notes="시스템 메뉴 조회", response=MenuMgPagingRVO.class)
	public CommonMessage getSysMenu(@ModelAttribute MenuMgPagingPVO menuMgPagingPVO) throws UserException {
		this.parameterLog("SysMenuMg[getSysMenu]", menuMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<MenuMgPagingRVO> returnVO = menuMgService.getSysMenu(menuMgPagingPVO);
		
		message.setOk();
		message.setData(returnVO);
		return message;
	}
	
	@PostMapping()
	@ApiOperation(value="postSysMenu", notes="시스템 메뉴 등록", response=Integer.class)
	public CommonMessage postSysMenu(@RequestBody MenuMgVO menuMgVO) throws UserException {
		this.parameterLog("SysMenuMg[postSysMenu]", menuMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int insCnt = menuMgService.postSysMenu(menuMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putSysMenu", notes="시스템 메뉴 수정", response=Integer.class)
	public CommonMessage putSysMenu(@RequestBody MenuMgVO menuMgVO) throws UserException {
		this.parameterLog("SysMenuMg[putSysMenu]", menuMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int updCnt = menuMgService.putSysMenu(menuMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteSysMenu", notes="시스템 메뉴 삭제", response=Integer.class)
	public CommonMessage deleteSysMenu(@RequestBody MenuMgVO menuMgVO) throws UserException {
		this.parameterLog("SysMenuMg[deleteSysMenu]", menuMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int delCnt = menuMgService.deleteSysMenu(menuMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}
