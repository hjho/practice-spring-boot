package hjho.prj.prct.biz.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.system.model.MgrGrpMgPagingVO;
import hjho.prj.prct.biz.system.model.MgrGrpMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/system/mgr/grp")
public class MgrGrpMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		return super.pageView("system", "mgrGrpMg");
	}
	
	@GetMapping("/get")
	public ModelAndView get(MgrGrpMgPagingVO mgrGrpMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.SYSTEM_MGR_GRP_API, mgrGrpMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView post(@PathVariable("method") String method, MgrGrpMgVO mgrGrpMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(URI.SYSTEM_MGR_GRP_API, mgrGrpMgVO);
				break;
			case UPD:
				output = commonService.put(URI.SYSTEM_MGR_GRP_API, mgrGrpMgVO);
				break;
			case DEL:
				output = commonService.delete(URI.SYSTEM_MGR_GRP_API, mgrGrpMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
