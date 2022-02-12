package hjho.prj.prct.biz.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.interfazz.MethodFunction;
import hjho.prj.prct.common.interfazz.MethodFunction.Function;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/test")
public class TestController extends CommonController {

	@Autowired
	private CommonService commonService;
	
	@MethodFunction(Function.M)
	@RequestMapping("/page")
	public ModelAndView testPage() {
		log.debug("[L] TEST PAGE MOVE");
		// NullPointerException
		// Map<String, String> nullMap = new HashMap<String, String>();
		// nullMap.get("null").toString();
		
		return this.pageView("fragment", "test");
	}
	
	@MethodFunction(Function.R)
	@RequestMapping("/{type}")
	public ModelAndView nullPointer(@PathVariable("type") String type) {
		log.debug("[L] Null Pointer Exception Test");
		// NullPointerException
		// Map<String, String> nullMap = new HashMap<String, String>();
		// nullMap.get("null").toString();
		
		// null, 
		String url = "/api/error/".concat(type);
		
		CommonMessage output = commonService.get(url, "");
		
		return super.jsonView(output);
	}
	
}
