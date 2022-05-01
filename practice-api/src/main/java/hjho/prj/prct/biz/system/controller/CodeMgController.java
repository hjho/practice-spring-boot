package hjho.prj.prct.biz.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.main.model.LoginRVO;
import hjho.prj.prct.biz.system.model.CodeMgPagingPVO;
import hjho.prj.prct.biz.system.model.CodeMgPagingRVO;
import hjho.prj.prct.biz.system.model.CodeMgVO;
import hjho.prj.prct.biz.system.service.CodeMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/system/code")
@Api(tags="SysCodeMg", value="시스템 공통코드 관리", description="시스템 공통코드 관리")
public class CodeMgController extends CommonController {
	
	@Autowired
	private CodeMgService codeMgService;
	
	@GetMapping()
	@ApiOperation(value="getSysCode", notes="시스템 공통코드 조회", response=LoginRVO.class)
	public CommonMessage getSysCode(@ModelAttribute CodeMgPagingPVO codeMgPagingPVO) throws UserException {
		this.parameterLog("SysCodeMg[getSysCode]", codeMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<CodeMgPagingRVO> output = codeMgService.getSysCode(codeMgPagingPVO);
		
		message.setOk();
		message.setData(output);
		return message;
	}
	
	@PostMapping()
	@ApiOperation(value="postSysCode", notes="시스템 공통코드 등록", response=Integer.class)
	public CommonMessage postSysCode(@RequestBody CodeMgVO codeMgVO) throws UserException {
		this.parameterLog("SysCodeMg[postSysCode]", codeMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int insCnt = codeMgService.postSysCode(codeMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putSysCode", notes="시스템 공통코드 수정", response=Integer.class)
	public CommonMessage putSysCode(@RequestBody CodeMgVO codeMgVO) throws UserException {
		this.parameterLog("SysCodeMg[putSysCode]", codeMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int updCnt = codeMgService.putSysCode(codeMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteSysCode", notes="시스템 공통코드 삭제", response=Integer.class)
	public CommonMessage deleteSysCode(@RequestBody CodeMgVO codeMgVO) throws UserException {
		this.parameterLog("SysCodeMg[deleteSysCode]", codeMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int delCnt = codeMgService.deleteSysCode(codeMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}
