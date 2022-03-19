package hjho.prj.prct.biz.system.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.system.model.MgrMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrMgVO;
import hjho.prj.prct.biz.system.service.MgrMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.VoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/system/mgr")
@Api(tags="SysMgrMg", value="시스템 관리자 관리", description="시스템 관리자 관리")
public class MgrMgController extends CommonController {
	
	@Autowired
	private MgrMgService mgrMgService;
	
	@GetMapping()
	@ApiOperation(value="getSysMgr", notes="시스템 메뉴 조회", response=MgrMgPagingRVO.class)
	public CommonMessage getSysMgr(@ModelAttribute MgrMgPagingPVO mgrMgPagingPVO) throws UserException {
		this.parameterLog("SysMgrMg[getSysMgr]", mgrMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<MgrMgPagingRVO> returnVO = mgrMgService.getSysMgr(mgrMgPagingPVO);
		
		message.setOk();
		message.setData(returnVO);
		return message;
	}
	
	@PostMapping()
	@ApiOperation(value="postSysMgr", notes="시스템 관리자 등록", response=Integer.class)
	public CommonMessage postSysMgr(@RequestBody MgrMgVO mgrMgVO) throws UserException {
		this.parameterLog("SysMgrMg[postSysMgr]", mgrMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int insCnt = mgrMgService.postSysMgr(mgrMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putSysMgr", notes="시스템 관리자 수정", response=Integer.class)
	public CommonMessage putSysMgr(@RequestBody MgrMgVO mgrMgVO) throws UserException {
		this.parameterLog("SysMgrMg[putSysMgr]", mgrMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int updCnt = mgrMgService.putSysMgr(mgrMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteSysMgr", notes="시스템 관리자 삭제", response=Integer.class)
	public CommonMessage deleteSysMgr(@RequestBody MgrMgVO mgrMgVO) throws UserException {
		this.parameterLog("SysMgrMg[deleteSysMgr]", mgrMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int delCnt = mgrMgService.deleteSysMgr(mgrMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}
