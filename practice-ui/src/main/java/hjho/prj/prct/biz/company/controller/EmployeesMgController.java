package hjho.prj.prct.biz.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.company.model.EmployeesMgPagingVO;
import hjho.prj.prct.biz.company.model.EmployeesMgVO;
import hjho.prj.prct.biz.company.model.JobsHsPagingVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;

@Controller
@RequestMapping("/company/employees")
public class EmployeesMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		ModelAndView mav = super.getPageMav();
		
		mav.addObject("boxDept", commonService.selectBox(URI.COMPANY_DEPARTMENTS_API));
		mav.addObject("boxJob", commonService.selectBox(URI.COMPANY_JOBS_API));
		
		return super.pageView(mav, "company", "employeesMg");
	}
	
	@GetMapping("/get")
	public ModelAndView get(EmployeesMgPagingVO employeesMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.COMPANY_EMPLOYEES_API, employeesMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	@GetMapping("/job/hs")
	public ModelAndView getJobsHs(JobsHsPagingVO jobsHsPagingVO) {
		
		CommonMessage output = commonService.get(URI.COMPANY_JOBS_HISTORY_API, jobsHsPagingVO);
		
		return super.pagingJsonView(output);
	}
	@PostMapping("/job/hs/delete")
	public ModelAndView postJobsHs(EmployeesMgVO employeesMgVO) {
		
		CommonMessage output = commonService.delete(URI.COMPANY_JOBS_HISTORY_API, employeesMgVO);
		
		if("0002".equals(output.getCode())) {
			output.setOk();
		}
		return super.jsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView post(@PathVariable("method") String method, EmployeesMgVO employeesMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(URI.COMPANY_EMPLOYEES_API, employeesMgVO);
				break;
			case UPD:
				output = commonService.put(URI.COMPANY_EMPLOYEES_API, employeesMgVO);
				break;
			case DEL:
				output = commonService.delete(URI.COMPANY_EMPLOYEES_API, employeesMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
