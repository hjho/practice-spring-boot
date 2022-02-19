package hjho.prj.prct.biz.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.company.model.DepartmentsMgPagingVO;
import hjho.prj.prct.biz.company.model.EmployeesMgPagingVO;
import hjho.prj.prct.biz.company.model.EmployeesMgVO;
import hjho.prj.prct.biz.company.model.JobsHsPagingVO;
import hjho.prj.prct.biz.company.model.JobsMgPagingVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.PracticeUrl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/company/employees")
public class EmployeesMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] EMPLOYEES PAGE MOVE");
		ModelAndView mav = super.getPageMav();
		
		mav.addObject("boxDept", commonService.selectBox(PracticeUrl.COMPANY_DEPARTMENTS_API));
		mav.addObject("boxJob", commonService.selectBox(PracticeUrl.COMPANY_JOBS_API));
		
		return super.pageView(mav, "company", "employeesMg");
	}
	
	@GetMapping()
	public ModelAndView getEmployees(EmployeesMgPagingVO employeesMgPagingVO) {
		
		CommonMessage output = commonService.get(PracticeUrl.COMPANY_EMPLOYEES_API, employeesMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	@GetMapping("/dept")
	public ModelAndView getDepartments(DepartmentsMgPagingVO departmentsMgPagingVO) {
		
		CommonMessage output = commonService.get(PracticeUrl.COMPANY_DEPARTMENTS_API, departmentsMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	@GetMapping("/job")
	public ModelAndView getJobs(JobsMgPagingVO jobsMgPagingVO) {
		
		CommonMessage output = commonService.get(PracticeUrl.COMPANY_JOBS_API, jobsMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	@GetMapping("/job/hs")
	public ModelAndView getJobsHs(JobsHsPagingVO jobsHsPagingVO) {
		
		CommonMessage output = commonService.get(PracticeUrl.COMPANY_JOBS_HISTORY_API, jobsHsPagingVO);
		
		return super.pagingJsonView(output);
	}
	@PostMapping("/job/hs/delete")
	public ModelAndView postJobsHs(EmployeesMgVO employeesMgVO) {
		
		CommonMessage output = commonService.delete(PracticeUrl.COMPANY_JOBS_HISTORY_API, employeesMgVO);
		
		if("0002".equals(output.getCode())) {
			output.setOk();
		}
		return super.jsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView postMgrDept(@PathVariable("method") String method, EmployeesMgVO employeesMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(PracticeUrl.COMPANY_EMPLOYEES_API, employeesMgVO);
				break;
			case UPD:
				output = commonService.put(PracticeUrl.COMPANY_EMPLOYEES_API, employeesMgVO);
				break;
			case DEL:
				output = commonService.delete(PracticeUrl.COMPANY_EMPLOYEES_API, employeesMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
