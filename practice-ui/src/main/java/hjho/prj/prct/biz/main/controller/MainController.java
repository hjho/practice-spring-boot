package hjho.prj.prct.biz.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.main.model.LoginVO;
import hjho.prj.prct.biz.main.service.LoginService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.interfazz.MethodFunction;
import hjho.prj.prct.common.interfazz.MethodFunction.Function;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/main")
@Slf4j
public class MainController extends CommonController {

	@Autowired
	private LoginService loginService;
	
	@MethodFunction(Function.M)
	@RequestMapping("/page")
	public ModelAndView mainPage() {
		log.debug("[L] MAIN PAGE MOVE");
		
		return this.pageView("fragment", "dashBord");
	}
	
	@MethodFunction(Function.R)
	@PostMapping("/login/proc")
	public ModelAndView proc(LoginVO loginVO) {
		List<Map<String, Object>> objList = new ArrayList<Map<String,Object>>();
		Map<String, Object> objMap = new HashMap<String, Object>();
		objMap.put("str", "script");
		objMap.put("num", 100);
		objList.add(objMap);
		loginVO.setObjMap(objMap);
		loginVO.setObjList(objList);
		log.debug("[L] MAIN LOGIN PROC : {}", loginVO);
		CommonMessage rspnData = loginService.proc(loginVO);
		
		return super.jsonView(rspnData);
	}
	
}
