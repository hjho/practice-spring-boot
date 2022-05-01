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

import hjho.prj.prct.biz.system.model.MgrGrpMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrGrpMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrGrpMgVO;
import hjho.prj.prct.biz.system.service.MgrGrpMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/system/mgr/grp")
@Api(tags="SysMgrGrpMg", value="시스템 관리자 그룹 관리", description="시스템 관리자 그룹 관리")
public class MgrGrpMgController extends CommonController {
	
	@Autowired
	private MgrGrpMgService mgrGrpMgService;
	
	@GetMapping()
	@ApiOperation(value="getSysMgrGrp", notes="시스템 관리자 그룹 조회", response=MgrGrpMgPagingRVO.class)
	public CommonMessage getSysMgrGrp(@ModelAttribute MgrGrpMgPagingPVO mgrGrpMgPagingPVO) throws UserException {
		this.parameterLog("MgrGrpMg[getSysMgrGrp]", mgrGrpMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<MgrGrpMgPagingRVO> returnList = mgrGrpMgService.getSysMgrGrp(mgrGrpMgPagingPVO);
		
		message.setOk();
		message.setData(returnList);
		return message;
	}
	
	@PostMapping()
	@ApiOperation(value="postSysMgrGrp", notes="시스템 관리자 그룹 등록", response=Integer.class)
	public CommonMessage postSysMgrGrp(@RequestBody MgrGrpMgVO mgrGrpMgVO) throws UserException {
		this.parameterLog("MgrGrpMg[postSysMgrGrp]", mgrGrpMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int insCnt = mgrGrpMgService.postSysMgrGrp(mgrGrpMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putSysMgrGrp", notes="시스템 관리자 그룹 수정", response=Integer.class)
	public CommonMessage putSysMgrGrp(@RequestBody MgrGrpMgVO mgrGrpMgVO) throws UserException {
		this.parameterLog("MgrGrpMg[putSysMgrGrp]", mgrGrpMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int updCnt = mgrGrpMgService.putSysMgrGrp(mgrGrpMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteSysMgrGrp", notes="시스템 관리자 그룹 삭제", response=Integer.class)
	public CommonMessage deleteSysMgrGrp(@RequestBody MgrGrpMgVO mgrGrpMgVO) throws UserException {
		this.parameterLog("MgrGrpMg[deleteSysMgrGrp]", mgrGrpMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int delCnt = mgrGrpMgService.deleteSysMgrGrp(mgrGrpMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}
