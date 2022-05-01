package hjho.prj.prct.biz.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.main.model.LoginPVO;
import hjho.prj.prct.biz.main.model.MgrInfoVO;
import hjho.prj.prct.biz.main.service.LoginService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.interfazz.MethodFunction;
import hjho.prj.prct.common.interfazz.MethodFunction.Function;
import hjho.prj.prct.common.util.VoUtil;
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
	
	@SuppressWarnings("unchecked")
	@MethodFunction(Function.R)
	@PostMapping("/proc")
	public ModelAndView proc(HttpServletRequest request, HttpServletResponse response, LoginPVO loginVO) {
		log.debug("[L] MAIN LOGIN PROC : {}", loginVO);
		CommonMessage message = loginService.proc(loginVO);
		
		if(message.isSuccess()) {
			List<Object> list = (ArrayList<Object>) VoUtil.objToVO(message.getData(), ArrayList.class);
			if(list.size() == 1) {
				// 유저 정보 설정
				MgrInfoVO mgrInfoVO = (MgrInfoVO) VoUtil.objToVO(list.get(0), MgrInfoVO.class);
				
				// 관리자 정보 저장
				loginService.setMgr(request, mgrInfoVO);
				// 유저 정보 저장
				loginService.setUser(request, mgrInfoVO);
				// 메뉴 권한 정보 설정
				loginService.setMenu(request, mgrInfoVO);
				// 토큰 발급 및 저장.
				loginService.setToken(request, mgrInfoVO);
				
				message.setMessage("로그인 되었습니다. \n메인 페이지로 이동합니다.");
			} else {
				message.setMessage("관리자 그룹을 선택해주세요.");
			}
		} 
		return super.jsonView(message);
	}
	
	@RequestMapping("/logout")
	public ModelAndView mainLogOut(HttpServletRequest request) {
		CommonMessage message = new CommonMessage();
		
		boolean isLogout = loginService.logout(request);
		
		if(isLogout) {
			message.setOk();
			message.setMessage("로그아웃 되었습니다. \n로그인페이지로 이동합니다.");
		} else {
			message.setError();
			message.setMessage("일시적인 오류가 발생했습니다. \n다시 시도해주세요.");
		}
		
		return super.jsonView(message);
	}
	
}
