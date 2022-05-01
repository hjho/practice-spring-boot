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

import hjho.prj.prct.biz.system.model.MgrAuthMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrAuthMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrAuthMgVO;
import hjho.prj.prct.biz.system.service.MgrAuthMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/system/mgr/auth")
@Api(tags="SysMgrAuthMg", value="시스템 관리자 그룹 권한 관리", description="시스템 관리자 권한 관리")
public class MgrAuthMgController extends CommonController {
	
	@Autowired
	private MgrAuthMgService mgrAuthMgService;
	
	@GetMapping()
	@ApiOperation(value="getSysMgrAuth", notes="시스템 관리자 그룹 역할 조회", response=MgrAuthMgPagingRVO.class)
	public CommonMessage getSysMgrAuth(@ModelAttribute MgrAuthMgPagingPVO mgrAuthMgPagingPVO) throws UserException {
		this.parameterLog("MgrAuthMg[getSysMgrAuth]", mgrAuthMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<MgrAuthMgPagingRVO> returnList = mgrAuthMgService.getSysMgrAuth(mgrAuthMgPagingPVO);
		
		message.setOk();
		message.setData(returnList);
		return message;
	}
	
	@GetMapping("/menu")
	@ApiOperation(value="getSysMgrAuthMenu", notes="시스템 관리자 그룹 역할 조회", response=MgrAuthMgPagingRVO.class)
	public CommonMessage getSysMgrAuthMenu(@ModelAttribute MgrAuthMgPagingPVO mgrAuthMgPagingPVO) throws UserException {
		this.parameterLog("MgrAuthMg[getSysMgrAuthMenu]", mgrAuthMgPagingPVO);
		CommonMessage message = new CommonMessage();
		
		List<MgrAuthMgPagingRVO> returnList = mgrAuthMgService.getSysMgrAuthMenu(mgrAuthMgPagingPVO);
		
		message.setOk();
		message.setData(returnList);
		return message;
	}
	
	@PostMapping()
	@ApiOperation(value="postSysMgrAuth", notes="시스템 관리자 그룹 권한 등록", response=Integer.class)
	public CommonMessage postSysMgrAuth(@RequestBody List<MgrAuthMgVO> mgrAuthMgList) throws UserException {
		this.parameterLog("MgrAuthMg[postSysMgrAuth]", mgrAuthMgList);
		
		CommonMessage output = new CommonMessage();
		
		int insCnt = 0;

		for (MgrAuthMgVO mgrAuthMgVO : mgrAuthMgList) {
			insCnt += mgrAuthMgService.postSysMgrAuth(mgrAuthMgVO);
		}
		
		output.setCode("0003");
		output.setArgs(new String[] {Integer.toString(insCnt)});
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putSysMgrAuth", notes="시스템 관리자 그룹 권한 수정", response=Integer.class)
	public CommonMessage putSysMgrAuth(@RequestBody List<MgrAuthMgVO> mgrAuthMgList) throws UserException {
		this.parameterLog("MgrAuthMg[putSysMgrAuth]", mgrAuthMgList);
		
		CommonMessage output = new CommonMessage();
		
		int updCnt = 0;
		
		for (MgrAuthMgVO mgrAuthMgVO : mgrAuthMgList) {
			updCnt += mgrAuthMgService.putSysMgrAuth(mgrAuthMgVO);
		}
		
		output.setCode("0001");
		output.setArgs(new String[] {Integer.toString(updCnt)});
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteSysMgrAuth", notes="시스템 관리자 그룹 권한 삭제", response=Integer.class)
	public CommonMessage deleteSysMgrAuth(@RequestBody List<MgrAuthMgVO> mgrAuthMgList) throws UserException {
		this.parameterLog("MgrAuthMg[deleteSysMgrAuth]", mgrAuthMgList);
		
		CommonMessage output = new CommonMessage();
		
		int delCnt = 0;
		
		for (MgrAuthMgVO mgrAuthMgVO : mgrAuthMgList) {
			delCnt += mgrAuthMgService.deleteSysMgrAuth(mgrAuthMgVO);
		}
		
		output.setCode("0002");
		output.setArgs(new String[] {Integer.toString(delCnt)});
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}
