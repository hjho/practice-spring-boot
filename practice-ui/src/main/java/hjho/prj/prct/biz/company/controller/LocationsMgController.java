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
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;

@Controller
@RequestMapping("/company/locations")
public class LocationsMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	/** 부서 위치 관리 페이지로 이동 */
	@RequestMapping("/page")
	public ModelAndView page() {
		ModelAndView mav = super.getPageMav();
		
		mav.addObject("boxCunt", commonService.selectBox(URI.GLOBAL_COUNTRIES_API));
		
		return super.pageView(mav, "company", "locationsMg");
	}
	
	/** 부서 위치 목록 조회 API 호출 */
	@GetMapping("/get")
	public ModelAndView get(LocationsMgPagingVO locationsMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.COMPANY_LOCATIONS_API, locationsMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	/** 부서 위치 목록 등록, 수정, 삭제 API 호출 */
	@PostMapping("/{method}")
	public ModelAndView post(@PathVariable("method") String method, LocationsMgVO locationsMgVO) {
		CommonMessage output = null;
		
		switch(method) {
			case INS:
				output = commonService.post(URI.COMPANY_LOCATIONS_API, locationsMgVO);
				break;
			case UPD:
				output = commonService.put(URI.COMPANY_LOCATIONS_API, locationsMgVO);
				break;
			case DEL:
				output = commonService.delete(URI.COMPANY_LOCATIONS_API, locationsMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
}
