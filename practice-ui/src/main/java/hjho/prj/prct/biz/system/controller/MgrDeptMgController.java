package hjho.prj.prct.biz.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.system.model.MgrDeptMgPagingVO;
import hjho.prj.prct.biz.system.model.MgrDeptMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mgr/dept")
public class MgrDeptMgController extends CommonController {
	
	private final String MGR_DEPT_API_URL = "/api/mgr/dept";
	
	@Autowired 
	private CommonService commonService;
	
	
	@RequestMapping("/page")
	public ModelAndView page() {
		log.debug("[L] MANAGE PAGE MOVE");
		
		return super.pageView("system", "mgrDeptMg");
	}
	
	@GetMapping()
	public ModelAndView getMgrDept(MgrDeptMgPagingVO MgrDeptMgPagingVO) {
		
		CommonMessage output = commonService.get(MGR_DEPT_API_URL, MgrDeptMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	/**
	 * <pre>
	 * Http Method 를 @GetMapping, @PostMapping 만 사용 하는 이유 <br>
	 *  - 톰캣은 파라미터를  GET과 POST만 파싱해준다. 
	 *  - PUT, DELETE는 별도의 추가 Config 필요.
	 * </pre>
	 * @param method
	 * @param mgrDeptMgVO
	 * @return
	 */
	@PostMapping("/{method}")
	public ModelAndView postMgrDept(@PathVariable("method") String method, MgrDeptMgVO mgrDeptMgVO) {
		CommonMessage output = null;
		
		switch(method) {
			case INS:
				output = commonService.post(MGR_DEPT_API_URL, mgrDeptMgVO);
				break;
			case UPD:
				output = commonService.put(MGR_DEPT_API_URL, mgrDeptMgVO);
				break;
			case DEL:
				output = commonService.delete(MGR_DEPT_API_URL, mgrDeptMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
