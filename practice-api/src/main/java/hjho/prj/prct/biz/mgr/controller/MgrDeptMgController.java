package hjho.prj.prct.biz.mgr.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.mgr.model.MgrDeptMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrDeptMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrDeptMgVO;
import hjho.prj.prct.biz.mgr.service.MgrDeptMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/mgr/dept")
@Api(tags="MgrDeptMg", value="관리자 부서 관리")
public class MgrDeptMgController extends CommonController {
	
	@Autowired
	private MgrDeptMgService mgrDeptMgService;
	
	@GetMapping()
	@ApiOperation(value="getMgrDept", notes="관리자 부서 조회", response=MgrDeptMgPagingRVO.class)
	public CommonMessage getMgrDept(@ModelAttribute MgrDeptMgPagingPVO mgrDeptMgPagingPVO) {
		this.parameterLog("MgrDeptMg[getMgrDept]", mgrDeptMgPagingPVO);
		CommonMessage output = new CommonMessage();
		
		List<MgrDeptMgPagingRVO> listMgrDept = mgrDeptMgService.getMgrDept(mgrDeptMgPagingPVO);
		
		output.setOk();
		output.setData(listMgrDept);
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postMgrDept", notes="관리자 부서 등록", response=Integer.class)
	public CommonMessage postMgrDept(@RequestBody MgrDeptMgVO mgrDeptMgVO) throws UserException {
		this.parameterLog("MgrDeptMg[postMgrDept]", mgrDeptMgVO);
		CommonMessage output = new CommonMessage();
		
		int insCnt = mgrDeptMgService.postMgrDept(mgrDeptMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putMgrDept", notes="관리자 부서 수정", response=Integer.class)
	public CommonMessage putMgrDept(@RequestBody MgrDeptMgVO mgrDeptMgVO) throws UserException {
		this.parameterLog("MgrDeptMg[putMgrDept]", mgrDeptMgVO);
		CommonMessage output = new CommonMessage();
		
		int updCnt = mgrDeptMgService.putMgrDept(mgrDeptMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteMgrDept", notes="관리자 부서 삭제", response=Integer.class)
	public CommonMessage deleteMgrDept(@RequestBody MgrDeptMgVO mgrDeptMgVO) throws UserException {
		this.parameterLog("MgrDeptMg[deleteMgrDept]", mgrDeptMgVO);
		CommonMessage output = new CommonMessage();
		
		int delCnt = mgrDeptMgService.deleteMgrDept(mgrDeptMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PostMapping("/test")
	@ApiOperation(value="test", notes="테스트", response=Integer.class)
	public CommonMessage test(@RequestBody @ApiParam(name="map", value="mgrDeptMgList") Map<String, List<MgrDeptMgVO>> map) {
		List<MgrDeptMgVO> mgrDeptMgList = map.get("mgrDeptMgList");
		
		this.parameterLog("MgrDeptMg[test]", mgrDeptMgList);
		
		CommonMessage output = new CommonMessage();
		
		output.setOk();
		output.setData(0);
		return output;
	}
	
}

