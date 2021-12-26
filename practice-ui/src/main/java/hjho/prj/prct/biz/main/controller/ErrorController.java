package hjho.prj.prct.biz.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.interfazz.MethodFunction;
import hjho.prj.prct.common.interfazz.MethodFunction.Function;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/error")
@Slf4j
public class ErrorController extends CommonController {

	@MethodFunction(Function.M)
	@RequestMapping("/page")
	public ModelAndView errorPage() {
		ModelAndView mav = new ModelAndView("error/errorPage");
		
		log.debug("[L] ERROR PAGE MOVE");
		
		return mav;
	}
	
}
