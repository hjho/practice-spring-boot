package hjho.prj.prct.biz.mgr.controller;

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

import hjho.prj.prct.biz.mgr.model.MgrLocMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrLocMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrLocMgVO;
import hjho.prj.prct.biz.mgr.service.MgrLocMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/mgr/loc")
@Api(tags="MgrLocMg", value="부서 위치 관리", description="관리자 부서 위치 관리")
public class MgrLocMgController extends CommonController {
	
	@Autowired 
	private MgrLocMgService mgrLocMgService;
	
	@GetMapping()
	@ApiOperation(value="getMgrLoc", notes="부서 위치 조회", response=MgrLocMgPagingRVO.class)
	public CommonMessage getMgrLoc(@ModelAttribute MgrLocMgPagingPVO mgrLocMgPagingPVO) {
		this.parameterLog("MgrLocMg[getMgrLoc]", mgrLocMgPagingPVO);
		CommonMessage output = new CommonMessage();
		
		List<MgrLocMgPagingRVO> listLoc = mgrLocMgService.getMgrLoc(mgrLocMgPagingPVO);
		
		output.setOk();
		output.setData(listLoc);
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postMgrLoc", notes="부서 위치 등록", response=Integer.class)
	public CommonMessage postMgrLoc(@RequestBody MgrLocMgVO mgrLocMgVO) throws UserException {
		this.parameterLog("MgrLocMg[postMgrLoc]", mgrLocMgVO);
		CommonMessage output = new CommonMessage();
		
		int insCnt = mgrLocMgService.postMgrLoc(mgrLocMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putMgrLoc", notes="부서 위치 수정", response=Integer.class)
	public CommonMessage putMgrLoc(@RequestBody MgrLocMgVO mgrLocMgVO) throws UserException {
		this.parameterLog("MgrLocMg[putMgrLoc]", mgrLocMgVO);
		CommonMessage output = new CommonMessage();
		
		int updCnt = mgrLocMgService.putMgrLoc(mgrLocMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteMgrLoc", notes="부서 위치 삭제", response=Integer.class)
	public CommonMessage deleteMgrLoc(@RequestBody MgrLocMgVO mgrLocMgVO) throws UserException {
		this.parameterLog("MgrLocMg[deleteMgrLoc]", mgrLocMgVO);
		CommonMessage output = new CommonMessage();
		
		int delCnt = mgrLocMgService.deleteMgrLoc(mgrLocMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}

