package hjho.prj.prct.biz.mgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.mgr.model.MgrDeptMgPagingVO;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgPagingVO;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgVO;
import hjho.prj.prct.biz.mgr.model.MgrJobHsMgPagingVO;
import hjho.prj.prct.biz.mgr.model.MgrJobMgPagingVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mgr/emp")
public class MgrEmpMgController extends CommonController {
	
	private final String MGR_DEPT_API_URL = "/api/mgr/dept";
	
	private final String MGR_JOB_API_URL = "/api/mgr/job";
	
	private final String MGR_JOB_HS_API_URL = "/api/mgr/job/hs";
	
	private final String MGR_EMP_API_URL = "/api/mgr/emp";
	
	@Autowired 
	private CommonService commonService;
	
	
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] EMPLOYEES PAGE MOVE");
		ModelAndView mav = super.getPageMav();
		
		mav.addObject("boxDept", commonService.selectBox(MGR_DEPT_API_URL));
		mav.addObject("boxJob", commonService.selectBox(MGR_JOB_API_URL));
		
		return super.pageView(mav, "mgr", "mgrEmpMg");
	}
	
	@GetMapping()
	public ModelAndView getMgEmp(MgrEmpMgPagingVO mgrEmpMgPagingVO) {
		
		CommonMessage output = commonService.get(MGR_EMP_API_URL, mgrEmpMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	@GetMapping("/dept")
	public ModelAndView getMgrDept(MgrDeptMgPagingVO mgrDeptMgPagingVO) {
		
		CommonMessage output = commonService.get(MGR_DEPT_API_URL, mgrDeptMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	@GetMapping("/job")
	public ModelAndView getMgrJob(MgrJobMgPagingVO mgrJobMgPagingVO) {
		
		CommonMessage output = commonService.get(MGR_JOB_API_URL, mgrJobMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	@GetMapping("/job/hs")
	public ModelAndView getMgrJobHs(MgrJobHsMgPagingVO mgrJobHsMgPagingVO) {
		
		CommonMessage output = commonService.get(MGR_JOB_HS_API_URL, mgrJobHsMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	@PostMapping("/job/hs/delete")
	public ModelAndView postMgrJobHs(MgrEmpMgVO mgrEmpMgVO) {
		
		CommonMessage output = commonService.delete(MGR_JOB_HS_API_URL, mgrEmpMgVO);
		
		if("0002".equals(output.getCode())) {
			output.setOk();
		}
		return super.jsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView postMgrDept(@PathVariable("method") String method, MgrEmpMgVO mgrEmpMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(MGR_EMP_API_URL, mgrEmpMgVO);
				break;
			case UPD:
				output = commonService.put(MGR_EMP_API_URL, mgrEmpMgVO);
				break;
			case DEL:
				output = commonService.delete(MGR_EMP_API_URL, mgrEmpMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
