package hjho.prj.prct.biz.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.sys.model.SysMenuMgPagingVO;
import hjho.prj.prct.biz.sys.model.SysMenuMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/sys/menu")
public class SysMenuMgController extends CommonController {
	
	private final String SYS_MENU_API_URL = "/api/sys/menu";
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] MENU PAGE MOVE");
		ModelAndView mav = super.getPageMav();
		
		mav.addObject("menuTree", commonService.selectBox("/api/main/menu"));
		
		return super.pageView(mav, "sys", "sysMenuMg");
	}
	
	@GetMapping()
	public ModelAndView getSysMenu(SysMenuMgPagingVO sysMenuMgPagingVO) {
		
		CommonMessage output = commonService.get(SYS_MENU_API_URL, sysMenuMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView postSysMenu(@PathVariable("method") String method, SysMenuMgVO sysMenuMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				sysMenuMgVO.setCretSysId("TEST_SYS");
				sysMenuMgVO.setCretMgrId("TEST_MGR");
				output = commonService.post(SYS_MENU_API_URL, sysMenuMgVO);
				break;
			case UPD:
				sysMenuMgVO.setUpdSysId("TEST_SYS");
				sysMenuMgVO.setUpdMgrId("TEST_MGR");
				output = commonService.put(SYS_MENU_API_URL, sysMenuMgVO);
				break;
			case DEL:
				output = commonService.delete(SYS_MENU_API_URL, sysMenuMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
