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

import hjho.prj.prct.biz.system.model.CodeDtMgPagingPVO;
import hjho.prj.prct.biz.system.model.CodeDtMgPagingRVO;
import hjho.prj.prct.biz.system.model.CodeDtMgVO;
import hjho.prj.prct.biz.system.service.CodeDtMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/system/code/detail")
@Api(tags="SysCodeDtMg", value="시스템 공통코드 상세 관리", description="시스템 공통코드 상세 관리")
public class CodeDtMgController extends CommonController {
	
	@Autowired
	private CodeDtMgService codeDtMgService;
	
	@GetMapping()
	@ApiOperation(value="getSysCodeDt", notes="시스템 공통코드 상세 조회", response=CodeDtMgPagingRVO.class)
	public CommonMessage getSysCodeDt(@ModelAttribute CodeDtMgPagingPVO codeDtMgPagingPVO) throws UserException {
		this.parameterLog("SysCodeDtMg[getSysCodeDt]", codeDtMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<CodeDtMgPagingRVO> output = codeDtMgService.getSysCodeDt(codeDtMgPagingPVO);
		
		message.setOk();
		message.setData(output);
		return message;
	}
	
	@PostMapping()
	@ApiOperation(value="postSysCodeDt", notes="시스템 공통코드 상세 등록", response=Integer.class)
	public CommonMessage postSysCodeDt(@RequestBody CodeDtMgVO codeDtMgVO) throws UserException {
		this.parameterLog("SysCodeDtMg[postSysCodeDt]", codeDtMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int insCnt = codeDtMgService.postSysCodeDt(codeDtMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putSysCodeDt", notes="시스템 공통코드 상세 수정", response=Integer.class)
	public CommonMessage putSysCodeDt(@RequestBody CodeDtMgVO codeDtMgVO) throws UserException {
		this.parameterLog("SysCodeDtMg[putSysCodeDt]", codeDtMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int updCnt = codeDtMgService.putSysCodeDt(codeDtMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping("/sort")
	@ApiOperation(value="putSysCodeDtSort", notes="시스템 공통코드 상세 정렬순서 수정", response=Integer.class)
	public CommonMessage putSysCodeDtSort(@RequestBody List<CodeDtMgVO> codeDtMgList) throws UserException {
		this.parameterLog("SysCodeDtMg[putSysCodeDtSort]", codeDtMgList);
		
		CommonMessage output = new CommonMessage();
		
		int updCnt = 0;
		
		for (CodeDtMgVO codeDtMgVO : codeDtMgList) {
			updCnt += codeDtMgService.putSysCodeDtSort(codeDtMgVO);
		}
		
		output.setCode("0001");
		output.setArgs(new String[] {Integer.toString(updCnt)});
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteSysCodeDt", notes="시스템 공통코드 상세 삭제", response=Integer.class)
	public CommonMessage deleteSysCodeDt(@RequestBody CodeDtMgVO codeDtMgVO) throws UserException {
		this.parameterLog("SysCodeDtMg[deleteSysCodeDt]", codeDtMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int delCnt = codeDtMgService.deleteSysCodeDt(codeDtMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}
