package hjho.prj.prct.biz.sys.controller;

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
import hjho.prj.prct.biz.sys.model.SysMenuMgPagingPVO;
import hjho.prj.prct.biz.sys.model.SysMenuMgPagingRVO;
import hjho.prj.prct.biz.sys.model.SysMenuMgVO;
import hjho.prj.prct.biz.sys.service.SysMenuMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/sys/menu")
@Api(tags="SysMenuMg", value="시스템 메뉴 관리", description="시스템 메뉴 관리")
public class SysMenuMgController extends CommonController {
	
	@Autowired
	private SysMenuMgService sysMenuMgService;
	
	@GetMapping()
	@ApiOperation(value="getSysMenu", notes="시스템 메뉴 조회", response=LoginRVO.class)
	public CommonMessage getSysMenu(@ModelAttribute SysMenuMgPagingPVO sysMenuMgPagingPVO) throws UserException {
		this.parameterLog("SysMenuMg[getSysMenu]", sysMenuMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<SysMenuMgPagingRVO> returnVO = sysMenuMgService.getSysMenu(sysMenuMgPagingPVO);
		
		message.setOk();
		message.setData(returnVO);
		return message;
	}
	
	@PostMapping()
	@ApiOperation(value="postSysMenu", notes="시스템 메뉴 등록", response=Integer.class)
	public CommonMessage postSysMenu(@RequestBody SysMenuMgVO sysMenuMgVO) throws UserException {
		this.parameterLog("SysMenuMg[postSysMenu]", sysMenuMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int insCnt = sysMenuMgService.postSysMenu(sysMenuMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putSysMenu", notes="시스템 메뉴 수정", response=Integer.class)
	public CommonMessage putSysMenu(@RequestBody SysMenuMgVO sysMenuMgVO) throws UserException {
		this.parameterLog("SysMenuMg[putSysMenu]", sysMenuMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int updCnt = sysMenuMgService.putSysMenu(sysMenuMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteSysMenu", notes="시스템 메뉴 삭제", response=Integer.class)
	public CommonMessage deleteSysMenu(@RequestBody SysMenuMgVO sysMenuMgVO) throws UserException {
		this.parameterLog("SysMenuMg[deleteSysMenu]", sysMenuMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int delCnt = sysMenuMgService.deleteSysMenu(sysMenuMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}
