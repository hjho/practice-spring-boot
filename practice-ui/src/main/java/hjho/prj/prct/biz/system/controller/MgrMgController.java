package hjho.prj.prct.biz.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.system.model.MgrMgPagingVO;
import hjho.prj.prct.biz.system.model.MgrMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;

@Controller
@RequestMapping("/system/mgr")
public class MgrMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		ModelAndView mav = super.getPageMav();
		
		mav.addObject("mgrStatCd", commonService.box("SYS_MGR_STAT_CD"));
		
		return super.pageView(mav, "system", "mgrMg");
	}
	
	@GetMapping("/get")
	public ModelAndView get(MgrMgPagingVO mgrMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.SYSTEM_MGR_API, mgrMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView post(@PathVariable("method") String method, MgrMgVO mgrMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(URI.SYSTEM_MGR_API, mgrMgVO);
				break;
			case UPD:
				output = commonService.put(URI.SYSTEM_MGR_API, mgrMgVO);
				break;
			case DEL:
				output = commonService.delete(URI.SYSTEM_MGR_API, mgrMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
