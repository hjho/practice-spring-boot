package hjho.prj.prct.biz.place.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.place.model.PlaceCuntMgPagingVO;
import hjho.prj.prct.biz.place.model.PlaceCuntMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/place/cunt")
public class PlaceCuntMgController extends CommonController {
	
	private final String PLACE_CUNT_API_URL = "/api/place/cunt";
	
	private final String PLACE_REGI_API_URL = "/api/place/regi";
	
	@Autowired 
	private CommonService commonService;
	
	
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] COUNTRY PAGE MOVE");
		ModelAndView mav = super.getPageMav();
		
		mav.addObject("boxRegi", commonService.selectBox(PLACE_REGI_API_URL));
		
		return super.pageView(mav, "place", "placeCuntMg");
	}
	
	@GetMapping()
	public ModelAndView getPlaceCunt(PlaceCuntMgPagingVO placeCuntMgPagingVO) {
		
		CommonMessage output = commonService.get(PLACE_CUNT_API_URL, placeCuntMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView postPlaceCunt(@PathVariable("method") String method, PlaceCuntMgVO placeCuntMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(PLACE_CUNT_API_URL, placeCuntMgVO);
				break;
			case UPD:
				output = commonService.put(PLACE_CUNT_API_URL, placeCuntMgVO);
				break;
			case DEL:
				output = commonService.delete(PLACE_CUNT_API_URL, placeCuntMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
