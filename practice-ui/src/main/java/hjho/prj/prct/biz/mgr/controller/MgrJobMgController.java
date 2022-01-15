package hjho.prj.prct.biz.mgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.mgr.model.MgrJobMgPagingVO;
import hjho.prj.prct.biz.mgr.model.MgrJobMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mgr/job")
public class MgrJobMgController extends CommonController {
	
	private final String MGR_JOB_API_URL = "/api/mgr/job";
	
	@Autowired 
	private CommonService commonService;
	
	
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] JOBS PAGE MOVE");
		ModelAndView mav = super.getPageMav();
		
		return super.pageView(mav, "mgr", "mgrJobMg");
	}
	
	@GetMapping()
	public ModelAndView getMgrJob(MgrJobMgPagingVO mgrJobMgPagingVO) {
		
		CommonMessage output = commonService.get(MGR_JOB_API_URL, mgrJobMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	/**
	 * <pre>
	 * Http Method 를 @GetMapping, @PostMapping 만 사용 하는 이유 <br>
	 *  - 톰캣은 파라미터를  GET과 POST만 파싱해준다. 
	 *  - PUT, DELETE는 별도의 추가 Config 필요.
	 * </pre>
	 * @param method
	 * @param mgrJobMgVO
	 * @return
	 */
	@PostMapping("/{method}")
	public ModelAndView postMgrJob(@PathVariable("method") String method, MgrJobMgVO mgrJobMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(MGR_JOB_API_URL, mgrJobMgVO);
				break;
			case UPD:
				output = commonService.put(MGR_JOB_API_URL, mgrJobMgVO);
				break;
			case DEL:
				output = commonService.delete(MGR_JOB_API_URL, mgrJobMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
