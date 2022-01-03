package hjho.prj.prct.biz.place.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.place.model.PlaceRegiMgPagingVO;
import hjho.prj.prct.biz.place.model.PlaceRegiMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/place/regi")
public class PlaceRegiMgController extends CommonController {
	
	private final String PLACE_REGI_API_URL = "/api/place/regi";
	
	@Autowired 
	private CommonService commonService;
	
	
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] REGION PAGE MOVE");
		return super.pageView("place", "placeRegiMg");
	}
	
	@GetMapping()
	public ModelAndView getPlaceRegi(PlaceRegiMgPagingVO placeRegiMgPagingVO) {
		
		CommonMessage output = commonService.get(PLACE_REGI_API_URL, placeRegiMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView postPlaceRegi(@PathVariable("method") String method, PlaceRegiMgVO placeRegiMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(PLACE_REGI_API_URL, placeRegiMgVO);
				break;
			case UPD:
				output = commonService.put(PLACE_REGI_API_URL, placeRegiMgVO);
				break;
			case DEL:
				output = commonService.delete(PLACE_REGI_API_URL, placeRegiMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
