package hjho.prj.prct.biz.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/error")
@Api(tags="ErrorTest", value="ErrorTest", description="Error 테스트 용")
public class ErrorTestController extends CommonController {
	
	@ApiOperation(value="NullPointerException", notes="/api/error/null", response=String.class)
	@GetMapping("/null")
	public CommonMessage nullPointer() {
		super.parameterLog("ErrorTest[nullPointer]", "");
		
		// NullPointerException
		Map<String, String> nullMap = new HashMap<String, String>();
		nullMap.get("null").toString();
		
		CommonMessage message = new CommonMessage();
		message.setOk();
		return message;
	}
	
}
