package hjho.prj.prct.biz.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.system.model.MenuMgPagingVO;
import hjho.prj.prct.biz.system.model.MenuMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/system/menu")
public class MenuMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] MENU PAGE MOVE");
		ModelAndView mav = super.getPageMav();
		
		mav.addObject("menuTree", commonService.selectBox("/api/main/menu"));
		
		return super.pageView(mav, "system", "menuMg");
	}
	
	@GetMapping()
	public ModelAndView getSysMenu(MenuMgPagingVO menuMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.SYSTEM_MENU_API, menuMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView postSysMenu(@PathVariable("method") String method, MenuMgVO menuMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				menuMgVO.setCretSysId("TEST_SYS");
				menuMgVO.setCretMgrId("TEST_MGR");
				output = commonService.post(URI.SYSTEM_MENU_API, menuMgVO);
				break;
			case UPD:
				menuMgVO.setUpdSysId("TEST_SYS");
				menuMgVO.setUpdMgrId("TEST_MGR");
				output = commonService.put(URI.SYSTEM_MENU_API, menuMgVO);
				break;
			case DEL:
				output = commonService.delete(URI.SYSTEM_MENU_API, menuMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
