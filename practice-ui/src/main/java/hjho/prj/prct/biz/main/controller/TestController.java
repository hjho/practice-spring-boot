package hjho.prj.prct.biz.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.main.model.LoginPVO;
import hjho.prj.prct.biz.main.model.LoginTestPVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.interfazz.MethodFunction;
import hjho.prj.prct.common.interfazz.MethodFunction.Function;
import hjho.prj.prct.common.util.VoUtil;
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
		return this.pageView("fragment", "test");
	}
	
	@MethodFunction(Function.R)
	@RequestMapping("/{type}")
	public ModelAndView nullPointer(@PathVariable("type") String type) {
		log.debug("[L] Null Pointer Exception Test");
		
		// null, 
		String url = "/api/error/".concat(type);
		
		CommonMessage output = commonService.get(url, "");
		
		return super.jsonView(output);
	}
	
	/**/
	public static void main(String[] args) {
		// Test 하기 위한 메인.
		TestController test = new TestController();
		
		// Copy Test
		// test.mapToVoTest();
		
		// String Test
		test.stringTest();
	}
	private void stringTest() {
		String origin = "/system/menu";
		
		String variable = "/system/menu/put";
		System.out.println(origin.endsWith("/menu"));
		System.out.println(variable.startsWith(origin));
		System.out.println(variable.substring(origin.length()));
		
	}
	/**
	 * A에서 B로 복사 될 때.
	 * B에는 A에 있는 변수가 다 있어야 한다.
	 */
	private void mapToVoTest() {
		// 1. 기본. A == B
		LoginPVO before = new LoginPVO();
		before.setMgrGrpId("mgrGrpId");
		before.setUserId("userId");
		before.setUserPw("userPw");
		System.out.println("before : " + before);
		
		Map<String, Object> after = VoUtil.objToMap(before);
		
		System.out.println("after  : " + after);
		System.out.println();
		
		// 2. B에 없는 변수를 카피 > 안됨.
		Map<String, Object> input2 = new HashMap<String, Object>();
		input2.put("mgrGrpId", "mgrGrpId");
		input2.put("userId", "userId");
		input2.put("userPw", "userPw");
		// input2.put("userNm", "userNm");
		System.out.println("before : " + input2);
		
		LoginPVO output2 = (LoginPVO) VoUtil.objToVO(input2, LoginPVO.class);
		
		System.out.println("after  : " + output2);
		System.out.println();
		
		// B에 없는 변수를 카피 > 안됨.
		LoginPVO before2 = new LoginPVO();
		before2.setMgrGrpId("mgrGrpId");
		before2.setUserId("userId");
		System.out.println("before : " + before2);
		
		LoginTestPVO after2 = (LoginTestPVO) VoUtil.objToVO(before2, LoginTestPVO.class);
		
		System.out.println("after  : " + after2);
		
	}
	
}
