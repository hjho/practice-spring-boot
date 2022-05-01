package hjho.prj.prct.biz.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.system.model.CodeMgPagingVO;
import hjho.prj.prct.biz.system.model.CodeMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;

@Controller
@RequestMapping("/system/code")
public class CodeMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		return super.pageView("system", "codeMg");
	}
	
	@GetMapping("/get")
	public ModelAndView get(CodeMgPagingVO codeMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.SYSTEM_CODE_API, codeMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView post(@PathVariable("method") String method, CodeMgVO codeMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(URI.SYSTEM_CODE_API, codeMgVO);
				break;
			case UPD:
				output = commonService.put(URI.SYSTEM_CODE_API, codeMgVO);
				break;
			case DEL:
				output = commonService.delete(URI.SYSTEM_CODE_API, codeMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
