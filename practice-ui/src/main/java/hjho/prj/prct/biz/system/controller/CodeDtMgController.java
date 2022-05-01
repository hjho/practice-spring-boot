package hjho.prj.prct.biz.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.system.model.CodeDtMgPagingVO;
import hjho.prj.prct.biz.system.model.CodeDtMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;

@Controller
@RequestMapping("/system/code/detail")
public class CodeDtMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@RequestMapping("/page")
	public ModelAndView page() {
		return super.pageView("system", "codeMg");
	}
	
	@GetMapping("/get")
	public ModelAndView get(CodeDtMgPagingVO codeDtMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.SYSTEM_CODE_DT_API, codeDtMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/sort/put")
	public ModelAndView put(@RequestBody List<CodeDtMgVO> codeDtMgList) {
		
		CommonMessage output = commonService.put(URI.SYSTEM_CODE_DT_API.concat("/sort"), codeDtMgList);
		if("0001".equals(output.getCode())) {
			output.setOk();
		}
		return super.jsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView post(@PathVariable("method") String method, CodeDtMgVO codeDtMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(URI.SYSTEM_CODE_DT_API, codeDtMgVO);
				break;
			case UPD:
				output = commonService.put(URI.SYSTEM_CODE_DT_API, codeDtMgVO);
				break;
			case DEL:
				output = commonService.delete(URI.SYSTEM_CODE_DT_API, codeDtMgVO);
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		
		return super.jsonView(output);
	}
	
}
