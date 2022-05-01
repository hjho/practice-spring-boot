package hjho.prj.prct.biz.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.company.model.EmployeesMgVO;
import hjho.prj.prct.biz.company.model.JobsHsPagingPVO;
import hjho.prj.prct.biz.company.model.JobsHsPagingRVO;
import hjho.prj.prct.biz.company.model.JobsMgPagingRVO;
import hjho.prj.prct.biz.company.service.JobsHsService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/company/jobs/history")
@Api(tags="JobsHs", value="자사 직책 히스토리 관리", description="자사 직책 히스토리 관리")
public class JobsHsController extends CommonController {
	
	@Autowired
	private JobsHsService jobsHsService;
	
	@GetMapping()
	@ApiOperation(value="getJobsHs", notes="자사 직책 히스토리 조회", response=JobsMgPagingRVO.class)
	public CommonMessage getJobsHs(@ModelAttribute JobsHsPagingPVO jobsHsPagingPVO) {
		this.parameterLog("JobsHsMg[getJobsHs]", jobsHsPagingPVO);
		CommonMessage output = new CommonMessage();
		
		List<JobsHsPagingRVO> listMgrJob = jobsHsService.getJobsHs(jobsHsPagingPVO);
		
		output.setOk();
		output.setData(listMgrJob);
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteJobsHs", notes="자사 직책 히스토리 삭제", response=Integer.class)
	public CommonMessage deleteJobsHs(@RequestBody EmployeesMgVO employeesMgVO) throws UserException {
		this.parameterLog("JobsHsMg[deleteJobsHs]", employeesMgVO);
		CommonMessage output = new CommonMessage();
		
		int delCnt = jobsHsService.deleteJobsHs(employeesMgVO);
		
		output.setCode("0002");
		output.setArgs(new String[] {Integer.toString(delCnt)});
		output.setData(delCnt);
		return output;
	}
	
}

