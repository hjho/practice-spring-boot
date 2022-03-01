package hjho.prj.prct.biz.company.controller;

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

import hjho.prj.prct.biz.company.model.JobsMgPagingPVO;
import hjho.prj.prct.biz.company.model.JobsMgPagingRVO;
import hjho.prj.prct.biz.company.model.JobsMgVO;
import hjho.prj.prct.biz.company.service.JobsMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/company/jobs")
@Api(tags="JobsMg", value="자사 직책 관리", description="자사 직책 관리")
public class JobsMgController extends CommonController {
	
	@Autowired
	private JobsMgService jobsMgService;
	
	@GetMapping()
	@ApiOperation(value="getJobs", notes="자사 직책 조회", response=JobsMgPagingRVO.class)
	public CommonMessage getJobs(@ModelAttribute JobsMgPagingPVO jobsMgPagingPVO) {
		this.parameterLog("JobsMg[getJobs]", jobsMgPagingPVO);
		CommonMessage output = new CommonMessage();
		
		List<JobsMgPagingRVO> listJobs = jobsMgService.getJobs(jobsMgPagingPVO);
		
		output.setOk();
		output.setData(listJobs);
		return output;
	}
	
	@GetMapping("/box")
	@ApiOperation(value="getJobsBox", notes="자사 직책 조회(selectBox)", response=JobsMgPagingRVO.class)
	public CommonMessage getJobsBox() {
		CommonMessage output = new CommonMessage();
		output.setOk();
		output.setData(jobsMgService.getJobsBox());
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postJobs", notes="자사 직책 등록", response=Integer.class)
	public CommonMessage postJobs(@RequestBody JobsMgVO jobsMgVO) throws UserException {
		this.parameterLog("JobsMg[postJobs]", jobsMgVO);
		CommonMessage output = new CommonMessage();
		
		int insCnt = jobsMgService.postJobs(jobsMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putJobs", notes="자사 직책 수정", response=Integer.class)
	public CommonMessage putJobs(@RequestBody JobsMgVO jobsMgVO) throws UserException {
		this.parameterLog("JobsMg[putJobs]", jobsMgVO);
		CommonMessage output = new CommonMessage();
		
		int updCnt = jobsMgService.putJobs(jobsMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteJobs", notes="자사 직책 삭제", response=Integer.class)
	public CommonMessage deleteJobs(@RequestBody JobsMgVO jobsMgVO) throws UserException {
		this.parameterLog("JobsMg[deleteJobs]", jobsMgVO);
		CommonMessage output = new CommonMessage();
		
		int delCnt = jobsMgService.deleteJobs(jobsMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}

