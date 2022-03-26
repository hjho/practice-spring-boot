package hjho.prj.prct.biz.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.system.model.MgrRoleMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrRoleMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrRoleMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/system/mgr/role")
@Api(tags="SysMgrRoleMg", value="시스템 관리자 그룹 역할 관리", description="시스템 관리자 역할 관리")
public class MgrRoleMgController extends CommonController {
	
	@GetMapping()
	@ApiOperation(value="getSysMgrRole", notes="시스템 관리자 그룹 역할 조회", response=MgrRoleMgPagingRVO.class)
	public CommonMessage getSysMgrRole(@ModelAttribute MgrRoleMgPagingPVO mgrRoleMgPagingPVO) throws UserException {
		this.parameterLog("MgrRoleMg[getSysMgrRole]", mgrRoleMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<MgrRoleMgPagingRVO> returnList = new ArrayList<MgrRoleMgPagingRVO>(); 
		//mgrMgService.getSysMgrRole(mgrMgPagingPVO);
		MgrRoleMgPagingRVO returnVO = new MgrRoleMgPagingRVO();
		returnVO.setMgrGrpId(mgrRoleMgPagingPVO.getMgrGrpId());
		returnVO.setMgrId("MGRID");
		returnVO.setMgrNm("MGRNM");
		returnVO.setApitDtm("2022-01-01 00:00:00");
		returnVO.setExprDtm("2022-12-31 23:59:59");
		returnVO.setTotalCnt(1L);
		returnVO.setUseYn("Y");
		returnList.add(returnVO);
		message.setOk();
		message.setData(returnList);
		return message;
	}
	
	@PostMapping()
	@ApiOperation(value="postSysMgrRole", notes="시스템 관리자 그룹 역할 등록", response=Integer.class)
	public CommonMessage postSysMgrRole(@RequestBody MgrRoleMgVO mgrRoleMgVO) throws UserException {
		this.parameterLog("MgrRoleMg[postSysMgrRole]", mgrRoleMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int insCnt = 1;
		// mgrMgService.postSysMgrRole(mgrMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putSysMgrRole", notes="시스템 관리자 그룹 역할 수정", response=Integer.class)
	public CommonMessage putSysMgrRole(@RequestBody MgrRoleMgVO mgrRoleMgVO) throws UserException {
		this.parameterLog("MgrRoleMg[putSysMgrRole]", mgrRoleMgVO);
		
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
	@ApiOperation(value="deleteSysMgrRole", notes="시스템 관리자 그룹 역할 삭제", response=Integer.class)
	public CommonMessage deleteSysMgrRole(@RequestBody MgrRoleMgVO mgrRoleMgVO) throws UserException {
		this.parameterLog("MgrRoleMg[deleteSysMgrRole]", mgrRoleMgVO);
		
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
