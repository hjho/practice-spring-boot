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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/system/mgr")
public class MgrMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] MGR PAGE MOVE");
		ModelAndView mav = super.getPageMav();
		
		return super.pageView(mav, "system", "mgrMg");
	}
	
	@GetMapping()
	public ModelAndView getMgr(MgrMgPagingVO mgrMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.SYSTEM_MGR_API, mgrMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView postMgr(@PathVariable("method") String method, MgrMgVO mgrMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				mgrMgVO.setCretSysId("TEST_SYS");
				mgrMgVO.setCretMgrId("TEST_MGR");
				output = commonService.post(URI.SYSTEM_MGR_API, mgrMgVO);
				break;
			case UPD:
				mgrMgVO.setUpdSysId("TEST_SYS");
				mgrMgVO.setUpdMgrId("TEST_MGR");
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
