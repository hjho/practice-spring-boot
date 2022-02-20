package hjho.prj.prct.biz.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.company.model.JobsMgPagingVO;
import hjho.prj.prct.biz.company.model.JobsMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/company/jobs")
public class JobsMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] JOBS PAGE MOVE");
		ModelAndView mav = super.getPageMav();
		
		return super.pageView(mav, "company", "jobsMg");
	}
	
	@GetMapping()
	public ModelAndView getJobs(JobsMgPagingVO jobsMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.COMPANY_JOBS_API, jobsMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView postJobs(@PathVariable("method") String method, JobsMgVO jobsMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(URI.COMPANY_JOBS_API, jobsMgVO);
				break;
			case UPD:
				output = commonService.put(URI.COMPANY_JOBS_API, jobsMgVO);
				break;
			case DEL:
				output = commonService.delete(URI.COMPANY_JOBS_API, jobsMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
