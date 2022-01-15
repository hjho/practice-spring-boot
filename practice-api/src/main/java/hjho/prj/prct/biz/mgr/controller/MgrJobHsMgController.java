package hjho.prj.prct.biz.mgr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.mgr.model.MgrEmpMgVO;
import hjho.prj.prct.biz.mgr.model.MgrJobHsMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrJobHsMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrJobMgPagingRVO;
import hjho.prj.prct.biz.mgr.service.MgrJobHsMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/mgr/job/hs")
@Api(tags="MgrJobMgHs", value="관리자 직책 히스토리 관리", description="관리자 직책 히스토리 관리")
public class MgrJobHsMgController extends CommonController {
	
	@Autowired
	private MgrJobHsMgService mgrJobHsMgService;
	
	@GetMapping()
	@ApiOperation(value="getMgrJobHs", notes="관리자 직책 히스토리 조회", response=MgrJobMgPagingRVO.class)
	public CommonMessage getMgrJobHs(@ModelAttribute MgrJobHsMgPagingPVO mgrJobHsMgPagingPVO) {
		this.parameterLog("MgrJobHsMg[getMgrJobHs]", mgrJobHsMgPagingPVO);
		CommonMessage output = new CommonMessage();
		
		List<MgrJobHsMgPagingRVO> listMgrJob = mgrJobHsMgService.getMgrJobHs(mgrJobHsMgPagingPVO);
		
		output.setOk();
		output.setData(listMgrJob);
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteMgrJobHs", notes="관리자 직책 히스토리 삭제", response=Integer.class)
	public CommonMessage deleteMgrJobHs(@RequestBody MgrEmpMgVO mgrEmpMgVO) throws UserException {
		this.parameterLog("MgrJobHsMg[deleteMgrJobHs]", mgrEmpMgVO);
		CommonMessage output = new CommonMessage();
		
		int delCnt = mgrJobHsMgService.deleteMgrJobHs(mgrEmpMgVO);
		
		output.setCode("0002");
		output.setArgs(new String[] {Integer.toString(delCnt)});
		output.setData(delCnt);
		return output;
	}
	
}

