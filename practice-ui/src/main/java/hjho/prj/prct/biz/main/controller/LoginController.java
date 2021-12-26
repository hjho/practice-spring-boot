package hjho.prj.prct.biz.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.common.clazz.CommonController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController extends CommonController {
	
	@RequestMapping("/page")
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView(LOGIN_PAGE);

		log.debug("[L] LOGIN PAGE MOVE");
		
		return mav;
	}
	
}
