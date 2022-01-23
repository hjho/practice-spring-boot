package hjho.prj.prct.biz.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.main.model.LoginPVO;
import hjho.prj.prct.biz.main.service.LoginService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.interfazz.MethodFunction;
import hjho.prj.prct.common.interfazz.MethodFunction.Function;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController extends CommonController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/page")
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView(LOGIN_PAGE);
		log.debug("[L] LOGIN PAGE MOVE");
		return mav;
	}
	
	@MethodFunction(Function.R)
	@PostMapping("/proc")
	public ModelAndView proc(HttpSession session, LoginPVO loginVO) {
		log.debug("[L] MAIN LOGIN PROC : {}", loginVO);
		log.debug("[L] MAIN LOGIN Session Id : {}", session.getId());
		
		CommonMessage rspnData = loginService.proc(loginVO);
		if("0000".equals(rspnData.getCode())) {
			
			// log.debug("[L] LOGIN RETURN DATA : {}", rspnData.getData());
			// 유저 정보 설정
			loginService.setUser(session, rspnData.getData());
			// 메뉴 권한 정보 설정
			loginService.setMenu(session, loginVO);
		}
		return super.jsonView(rspnData);
	}
}
