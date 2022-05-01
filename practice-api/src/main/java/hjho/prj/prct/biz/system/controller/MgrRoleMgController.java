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

import hjho.prj.prct.biz.system.model.MgrMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrRoleMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrRoleMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrRoleMgVO;
import hjho.prj.prct.biz.system.service.MgrRoleMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/system/mgr/role")
@Api(tags="SysMgrRoleMg", value="시스템 관리자 그룹 역할 관리", description="시스템 관리자 역할 관리")
public class MgrRoleMgController extends CommonController {
	
	@Autowired
	private MgrRoleMgService mgrRoleMgService;
	
	@GetMapping()
	@ApiOperation(value="getSysMgrRole", notes="시스템 관리자 그룹 역할 조회", response=MgrRoleMgPagingRVO.class)
	public CommonMessage getSysMgrRole(@ModelAttribute MgrRoleMgPagingPVO mgrRoleMgPagingPVO) throws UserException {
		this.parameterLog("MgrRoleMg[getSysMgrRole]", mgrRoleMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<MgrRoleMgPagingRVO> returnList = mgrRoleMgService.getSysMgrRole(mgrRoleMgPagingPVO); 

		message.setOk();
		message.setData(returnList);
		return message;
	}
	
	@GetMapping("/mgr")
	@ApiOperation(value="getSysMgrRoleMgr", notes="시스템 관리자 그룹 역할의 소속 관리자 조회", response=MgrRoleMgPagingRVO.class)
	public CommonMessage getSysMgrRoleMgr(@ModelAttribute MgrRoleMgPagingPVO mgrRoleMgPagingPVO) throws UserException {
		this.parameterLog("MgrRoleMg[getSysMgrRoleMgr]", mgrRoleMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<MgrMgPagingRVO> returnList = mgrRoleMgService.getSysMgrRoleMgr(mgrRoleMgPagingPVO); 
		
		message.setOk();
		message.setData(returnList);
		return message;
	}
	
	@PostMapping()
	@ApiOperation(value="postSysMgrRole", notes="시스템 관리자 그룹 역할 등록", response=Integer.class)
	public CommonMessage postSysMgrRole(@RequestBody List<MgrRoleMgVO> mgrRoleMgList) throws UserException {
		this.parameterLog("MgrRoleMg[postSysMgrRole]", mgrRoleMgList);
		
		CommonMessage output = new CommonMessage();
		
		int insCnt = 0;
		
		for (MgrRoleMgVO mgrRoleMgVO : mgrRoleMgList) {
			insCnt += mgrRoleMgService.postSysMgrRole(mgrRoleMgVO);
		}
		
		output.setCode("0003");
		output.setArgs(new String[] {Integer.toString(insCnt)});
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putSysMgrRole", notes="시스템 관리자 그룹 역할 수정", response=Integer.class)
	public CommonMessage putSysMgrRole(@RequestBody List<MgrRoleMgVO> mgrRoleMgList) throws UserException {
		this.parameterLog("MgrRoleMg[putSysMgrRole]", mgrRoleMgList);
		
		CommonMessage output = new CommonMessage();
		
		int updCnt = 0;
		
		for (MgrRoleMgVO mgrRoleMgVO : mgrRoleMgList) {
			updCnt += mgrRoleMgService.putSysMgrRole(mgrRoleMgVO);
		}
		
		output.setCode("0001");
		output.setArgs(new String[] {Integer.toString(updCnt)});
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteSysMgrRole", notes="시스템 관리자 그룹 역할 삭제", response=Integer.class)
	public CommonMessage deleteSysMgrRole(@RequestBody List<MgrRoleMgVO> mgrRoleMgList) throws UserException {
		this.parameterLog("MgrRoleMg[deleteSysMgrRole]", mgrRoleMgList);
		
		CommonMessage output = new CommonMessage();
		
		int delCnt = 0;
		
		for (MgrRoleMgVO mgrRoleMgVO : mgrRoleMgList) {
			delCnt += mgrRoleMgService.deleteSysMgrRole(mgrRoleMgVO);
		}
		
		output.setCode("0002");
		output.setArgs(new String[] {Integer.toString(delCnt)});
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}
