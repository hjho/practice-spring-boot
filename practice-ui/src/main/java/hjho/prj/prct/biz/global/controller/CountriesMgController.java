package hjho.prj.prct.biz.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.global.model.CountriesMgPagingVO;
import hjho.prj.prct.biz.global.model.CountriesMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;

@Controller
@RequestMapping("/global/countries")
public class CountriesMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		ModelAndView mav = super.getPageMav();
		
		mav.addObject("boxRegi", commonService.selectBox(URI.GLOBAL_REGIONS_API));
		
		return super.pageView(mav, "global", "countriesMg");
	}
	
	@GetMapping("/get")
	public ModelAndView get(CountriesMgPagingVO countriesMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.GLOBAL_COUNTRIES_API, countriesMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView post(@PathVariable("method") String method, CountriesMgVO countriesMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(URI.GLOBAL_COUNTRIES_API, countriesMgVO);
				break;
			case UPD:
				output = commonService.put(URI.GLOBAL_COUNTRIES_API, countriesMgVO);
				break;
			case DEL:
				output = commonService.delete(URI.GLOBAL_COUNTRIES_API, countriesMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
