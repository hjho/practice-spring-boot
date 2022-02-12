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

import hjho.prj.prct.biz.mgr.model.MgrJobMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrJobMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrJobMgVO;
import hjho.prj.prct.biz.mgr.service.MgrJobMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/mgr/job")
@Api(tags="MgrJobMg", value="관리자 직책 관리", description="관리자 직책 관리")
public class MgrJobMgController extends CommonController {
	
	@Autowired
	private MgrJobMgService mgrJobMgService;
	
	@GetMapping()
	@ApiOperation(value="getMgrJob", notes="관리자 직책 조회", response=MgrJobMgPagingRVO.class)
	public CommonMessage getMgrJob(@ModelAttribute MgrJobMgPagingPVO mgrJobMgPagingPVO) {
		this.parameterLog("MgrJobMg[getMgrJob]", mgrJobMgPagingPVO);
		CommonMessage output = new CommonMessage();
		
		List<MgrJobMgPagingRVO> listMgrJob = mgrJobMgService.getMgrJob(mgrJobMgPagingPVO);
		
		output.setOk();
		output.setData(listMgrJob);
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postMgrJob", notes="관리자 직책 등록", response=Integer.class)
	public CommonMessage postMgrJob(@RequestBody MgrJobMgVO mgrJobMgVO) throws UserException {
		this.parameterLog("MgrJobMg[postMgrJob]", mgrJobMgVO);
		CommonMessage output = new CommonMessage();
		
		int insCnt = mgrJobMgService.postMgrJob(mgrJobMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putMgrJob", notes="관리자 직책 수정", response=Integer.class)
	public CommonMessage putMgrJob(@RequestBody MgrJobMgVO mgrJobMgVO) throws UserException {
		this.parameterLog("MgrJobMg[putMgrJob]", mgrJobMgVO);
		CommonMessage output = new CommonMessage();
		
		int updCnt = mgrJobMgService.putMgrJob(mgrJobMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteMgrJob", notes="관리자 직책 삭제", response=Integer.class)
	public CommonMessage deleteMgrJob(@RequestBody MgrJobMgVO mgrJobMgVO) throws UserException {
		this.parameterLog("MgrJobMg[deleteMgrJob]", mgrJobMgVO);
		CommonMessage output = new CommonMessage();
		
		int delCnt = mgrJobMgService.deleteMgrJob(mgrJobMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}

