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

import hjho.prj.prct.biz.system.model.MgrAuthMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrAuthMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrAuthMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/system/mgr/auth")
@Api(tags="SysMgrAuthMg", value="시스템 관리자 그룹 권한 관리", description="시스템 관리자 권한 관리")
public class MgrAuthMgController extends CommonController {
	
	@GetMapping()
	@ApiOperation(value="getSysMgrAuth", notes="시스템 관리자 그룹 역할 조회", response=MgrAuthMgPagingRVO.class)
	public CommonMessage getSysMgrAuth(@ModelAttribute MgrAuthMgPagingPVO mgrAuthMgPagingPVO) throws UserException {
		this.parameterLog("MgrAuthMg[getSysMgrAuth]", mgrAuthMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<MgrAuthMgPagingRVO> returnList = new ArrayList<MgrAuthMgPagingRVO>(); 
		//mgrMgService.getSysMgrAuth(mgrMgPagingPVO);
		MgrAuthMgPagingRVO returnVO = new MgrAuthMgPagingRVO();
		returnVO.setMgrGrpId(mgrAuthMgPagingPVO.getMgrGrpId());
		returnVO.setMenuId("MENUID");
		returnVO.setMenuNm("MENUNM");
		returnVO.setCretAuthYn("Y");
		returnVO.setReadAuthYn("Y");
		returnVO.setUpdAuthYn("Y");
		returnVO.setDelAuthYn("Y");
		returnVO.setExptAuthYn("Y");
		returnVO.setPrivDataReadAuthYn("Y");
		returnVO.setUseYn("Y");
		returnVO.setTotalCnt(1L);
		returnList.add(returnVO);
		message.setOk();
		message.setData(returnList);
		return message;
	}
	
	@PostMapping()
	@ApiOperation(value="postSysMgrAuth", notes="시스템 관리자 그룹 권한 등록", response=Integer.class)
	public CommonMessage postSysMgrAuth(@RequestBody MgrAuthMgVO mgrAuthMgVO) throws UserException {
		this.parameterLog("MgrAuthMg[postSysMgrAuth]", mgrAuthMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int insCnt = 1;
		// mgrMgService.postSysMgrAuth(mgrMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putSysMgrAuth", notes="시스템 관리자 그룹 권한 수정", response=Integer.class)
	public CommonMessage putSysMgrAuth(@RequestBody MgrAuthMgVO mgrAuthMgVO) throws UserException {
		this.parameterLog("MgrAuthMg[putSysMgrAuth]", mgrAuthMgVO);
		
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
	@ApiOperation(value="deleteSysMgrAuth", notes="시스템 관리자 그룹 권한 삭제", response=Integer.class)
	public CommonMessage deleteSysMgrAuth(@RequestBody MgrAuthMgVO mgrAuthMgVO) throws UserException {
		this.parameterLog("MgrAuthMg[deleteSysMgrAuth]", mgrAuthMgVO);
		
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
