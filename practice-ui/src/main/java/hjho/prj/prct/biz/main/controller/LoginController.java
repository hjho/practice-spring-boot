package hjho.prj.prct.biz.main.controller;

import javax.servlet.http.HttpServletRequest;

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
import hjho.prj.prct.common.util.SessionUtil;
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
	public ModelAndView proc(HttpServletRequest request, LoginPVO loginVO) {
		log.debug("[L] MAIN LOGIN PROC : {}", loginVO);
		
		CommonMessage rspnData = loginService.proc(loginVO);
		
		if("0000".equals(rspnData.getCode())) {
			// 유저 정보 설정
			loginService.setUser(request, rspnData.getData());
			// 메뉴 권한 정보 설정
			loginService.setMenu(request, loginVO);
		}
		return super.jsonView(rspnData);
	}
	
	@RequestMapping("/logout")
	public ModelAndView mainLogOut() {
		CommonMessage message = new CommonMessage();
		SessionUtil.logout();
		message.setOk();
		message.setMessage("로그아웃 되었습니다. \n로그인페이지로 이동합니다.");
		return super.jsonView(message);
	}
	
	
}
