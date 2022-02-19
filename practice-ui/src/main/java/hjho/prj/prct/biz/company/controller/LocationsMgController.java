package hjho.prj.prct.biz.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.company.model.LocationsMgPagingVO;
import hjho.prj.prct.biz.company.model.LocationsMgVO;
import hjho.prj.prct.biz.global.model.CountriesMgPagingVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.PracticeUrl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/company/locations")
public class LocationsMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	/** 부서 위치 관리 페이지로 이동 */
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] LOCATION PAGE MOVE");
		ModelAndView mav = super.getPageMav();
		
		mav.addObject("boxCunt", commonService.selectBox(PracticeUrl.GLOBAL_COUNTRIES_API));
		
		return super.pageView(mav, "company", "locationsMg");
	}
	
	/** 부서 위치 목록 조회 API 호출 */
	@GetMapping()
	public ModelAndView getLocations(LocationsMgPagingVO locationsMgPagingVO) {
		
		CommonMessage output = commonService.get(PracticeUrl.COMPANY_LOCATIONS_API, locationsMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	/** 국가 목록 조회 API 호출 */
	@GetMapping("/cunt")
	public ModelAndView getCountries(CountriesMgPagingVO cuntMgPagingVO) {
		
		CommonMessage output = commonService.get(PracticeUrl.GLOBAL_COUNTRIES_API, cuntMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	/** 부서 위치 목록 등록, 수정, 삭제 API 호출 */
	@PostMapping("/{method}")
	public ModelAndView postLocations(@PathVariable("method") String method, LocationsMgVO locationsMgVO) {
		CommonMessage output = null;
		
		switch(method) {
			case INS:
				output = commonService.post(PracticeUrl.COMPANY_LOCATIONS_API, locationsMgVO);
				break;
			case UPD:
				output = commonService.put(PracticeUrl.COMPANY_LOCATIONS_API, locationsMgVO);
				break;
			case DEL:
				output = commonService.delete(PracticeUrl.COMPANY_LOCATIONS_API, locationsMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
}
