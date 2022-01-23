package hjho.prj.prct.biz.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.interfazz.MethodFunction;
import hjho.prj.prct.common.interfazz.MethodFunction.Function;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class MainController extends CommonController {

	@RequestMapping()
	public ModelAndView rootPage() {
		return this.mainPage();
	}
	
	@MethodFunction(Function.M)
	@RequestMapping("/main/page")
	public ModelAndView mainPage() {
		log.debug("[L] MAIN PAGE MOVE");
		
		return this.pageView("fragment", "dashBord");
	}
	
	
}
