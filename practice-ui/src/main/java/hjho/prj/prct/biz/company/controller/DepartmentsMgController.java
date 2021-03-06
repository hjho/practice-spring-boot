package hjho.prj.prct.biz.company.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.company.model.DepartmentsMgPagingVO;
import hjho.prj.prct.biz.company.model.DepartmentsMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;

@Controller
@RequestMapping("/company/departments")
public class DepartmentsMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		ModelAndView mav = super.getPageMav();
		
		mav.addObject("boxCunt", commonService.selectBox(URI.GLOBAL_COUNTRIES_API));
		
		return super.pageView(mav, "company", "departmentsMg");
	}
	
	@GetMapping("/get")
	public ModelAndView get(DepartmentsMgPagingVO departmentsMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.COMPANY_DEPARTMENTS_API, departmentsMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	/**
	 * <pre>
	 * Http Method 를 @GetMapping, @PostMapping 만 사용 하는 이유 <br>
	 *  - 톰캣은 파라미터를  GET과 POST만 파싱해준다. 
	 *  - PUT, DELETE는 별도의 추가 Config 필요.
	 * </pre>
	 * @param method
	 * @param departmentsMgVO
	 * @return
	 */
	@PostMapping("/{method}")
	public ModelAndView post(@PathVariable("method") String method, DepartmentsMgVO departmentsMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(URI.COMPANY_DEPARTMENTS_API, departmentsMgVO);
				break;
			case UPD:
				output = commonService.put(URI.COMPANY_DEPARTMENTS_API, departmentsMgVO);
				break;
			case DEL:
				output = commonService.delete(URI.COMPANY_DEPARTMENTS_API, departmentsMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
	/**
	 * <pre>
	 * ajax 에서 JSON(application/json)으로 파싱된 데이터를 List로 받은 후 Map에 넣어 전송.
	 * Map에 넣은 이유는 SimpleXssFilter getInputStream 함수에서 데이터를 Map으로 파싱한다.
	 * </pre> 
	 * @param DepartmentsMgList
	 * @return
	 */
	@PostMapping("/test")
	public ModelAndView test(@RequestBody List<DepartmentsMgVO> departmentsMgList) {
		Map<String, List<DepartmentsMgVO>> map = new HashMap<>();
		map.put("departmentsMgList", departmentsMgList);
		
		CommonMessage output = commonService.post(URI.COMPANY_DEPARTMENTS_API.concat("/test"), map);

		return super.jsonView(output);
	}
	
}
