package hjho.prj.prct.biz.sys.controller;

import java.util.List;

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
import hjho.prj.prct.biz.sys.model.SysMenuMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/sys/mgr")
@Api(tags="SysMgrMg", value="시스템 관리자 관리", description="시스템 관리자 관리")
public class SysMgrMgController extends CommonController {
	
//	@Autowired
//	private SysMgrMgService sysMgrMgService;
	
	@GetMapping()
	@ApiOperation(value="getSysMgr", notes="시스템 관리자 조회", response=LoginRVO.class)
	public CommonMessage getSysMgr(@ModelAttribute SysMenuMgPagingPVO sysMenuMgPagingPVO) throws UserException {
		this.parameterLog("SysMgrMg[getSysMgr]", sysMenuMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<String> returnVO = null;
		
		message.setOk();
		message.setData(returnVO);
		return message;
	}
	
	@PostMapping("/getToken")
	@ApiOperation(value="getToken", notes="시스템 관리자 리프레쉬 토큰 조회", response=String.class)
	public CommonMessage getToken(@ModelAttribute SysMenuMgPagingPVO sysMenuMgPagingPVO) throws UserException {
		this.parameterLog("SysMgrMg[getToken]", sysMenuMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		String refreshToken = null;
		
		message.setOk();
		message.setData(refreshToken);
		return message;
	}
	
	@PostMapping()
	@ApiOperation(value="postSysMgr", notes="시스템 관리자 등록", response=Integer.class)
	public CommonMessage postSysMgr(@RequestBody SysMenuMgVO sysMenuMgVO) throws UserException {
		this.parameterLog("SysMgrMg[postSysMgr]", sysMenuMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int insCnt = 1;
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putSysMgr", notes="시스템 관리자 수정", response=Integer.class)
	public CommonMessage putSysMgr(@RequestBody SysMenuMgVO sysMenuMgVO) throws UserException {
		this.parameterLog("SysMgrMg[putSysMgr]", sysMenuMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int updCnt = 1;
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteSysMgr", notes="시스템 관리자 삭제", response=Integer.class)
	public CommonMessage deleteSysMgr(@RequestBody SysMenuMgVO sysMenuMgVO) throws UserException {
		this.parameterLog("SysMgrMg[deleteSysMgr]", sysMenuMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int delCnt = 1;
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}
