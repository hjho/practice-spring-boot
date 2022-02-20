package hjho.prj.prct.biz.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.global.model.RegionsMgPagingVO;
import hjho.prj.prct.biz.global.model.RegionsMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/global/regions")
public class RegionsMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] REGION PAGE MOVE");
		return super.pageView("global", "regionsMg");
	}
	
	@GetMapping()
	public ModelAndView getRegions(RegionsMgPagingVO regionsMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.GLOBAL_REGIONS_API, regionsMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView postRegions(@PathVariable("method") String method, RegionsMgVO regionsMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(URI.GLOBAL_REGIONS_API, regionsMgVO);
				break;
			case UPD:
				output = commonService.put(URI.GLOBAL_REGIONS_API, regionsMgVO);
				break;
			case DEL:
				output = commonService.delete(URI.GLOBAL_REGIONS_API, regionsMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
