package hjho.prj.prct.biz.mgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.mgr.model.MgrLocMgPagingVO;
import hjho.prj.prct.biz.mgr.model.MgrLocMgVO;
import hjho.prj.prct.biz.place.model.PlaceCuntMgPagingVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mgr/loc")
public class MgrLocMgController extends CommonController {
	
	private final String MGR_LOC_API_URL = "/api/mgr/loc";
	
	private final String PLACE_CUNT_API_URL = "/api/place/cunt";
	
	@Autowired 
	private CommonService commonService;
	
	/** 부서 위치 관리 페이지로 이동 */
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] LOCATION PAGE MOVE");
		ModelAndView mav = super.getPageMav();
		
		mav.addObject("boxCunt", commonService.selectBox(PLACE_CUNT_API_URL));
		
		return super.pageView(mav, "mgr", "mgrLocMg");
	}
	
	/** 부서 위치 목록 조회 API 호출 */
	@GetMapping()
	public ModelAndView getMgrLoc(MgrLocMgPagingVO mgrLocMgPagingVO) {
		
		CommonMessage output = commonService.get(MGR_LOC_API_URL, mgrLocMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	/** 국가 목록 조회 API 호출 */
	@GetMapping("/cunt")
	public ModelAndView getPlaceCunt(PlaceCuntMgPagingVO cuntMgPagingVO) {
		
		CommonMessage output = commonService.get(PLACE_CUNT_API_URL, cuntMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	/** 부서 위치 목록 등록, 수정, 삭제 API 호출 */
	@PostMapping("/{method}")
	public ModelAndView postMgrLoc(@PathVariable("method") String method, MgrLocMgVO mgrLocMgVO) {
		CommonMessage output = null;
		
		switch(method) {
			case INS:
				output = commonService.post(MGR_LOC_API_URL, mgrLocMgVO);
				break;
			case UPD:
				output = commonService.put(MGR_LOC_API_URL, mgrLocMgVO);
				break;
			case DEL:
				output = commonService.delete(MGR_LOC_API_URL, mgrLocMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
}
