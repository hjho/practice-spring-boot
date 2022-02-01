package hjho.prj.prct.biz.main.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.interfazz.MethodFunction;
import hjho.prj.prct.common.interfazz.MethodFunction.Function;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/main")
@Slf4j
public class MainController extends CommonController {

	/*
	@RequestMapping()
	public ModelAndView rootPage() {
		return this.mainPage();
	}*/
	
	@MethodFunction(Function.M)
	@RequestMapping("/page")
	public ModelAndView mainPage() {
		log.debug("[L] MAIN PAGE MOVE");
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		log.debug("[L] MAIN PAGE Context : {}", context);
		if(authentication != null) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			log.debug("[L] MAIN PAGE Authenticated : {}", authentication.isAuthenticated());
			log.debug("[L] MAIN PAGE Name          : {}", authentication.getName());
			log.debug("[L] MAIN PAGE Credentials   : {}", authentication.getCredentials());
			log.debug("[L] MAIN PAGE Details       : {}", authentication.getDetails());
			log.debug("[L] MAIN PAGE Principal     : {}", authentication.getPrincipal());
			log.debug("[L] MAIN PAGE Authorities   : {}", authorities);
		}
		return this.pageView("fragment", "dashBord");
	}
	
}
