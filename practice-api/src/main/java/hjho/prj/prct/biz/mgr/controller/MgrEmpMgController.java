package hjho.prj.prct.biz.mgr.controller;

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

import hjho.prj.prct.biz.mgr.model.MgrEmpMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgVO;
import hjho.prj.prct.biz.mgr.service.MgrEmpMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/mgr/emp")
@Api(tags="MgrEmpMg", value="관리자 사원 관리")
public class MgrEmpMgController extends CommonController {
	
	@Autowired
	private MgrEmpMgService mgrEmpMgService;
	
	@GetMapping()
	@ApiOperation(value="getMgrEmp", notes="관리자 부서 조회", response=MgrEmpMgPagingRVO.class)
	public CommonMessage getMgrEmp(@ModelAttribute MgrEmpMgPagingPVO mgrEmpMgPagingPVO) {
		this.parameterLog("MgrEmpMg[getMgrEmp]", mgrEmpMgPagingPVO);
		
		CommonMessage output = new CommonMessage();
		
		List<MgrEmpMgPagingRVO> listMgrEmp = mgrEmpMgService.getMgrEmp(mgrEmpMgPagingPVO);
		
		output.setOk();
		output.setData(listMgrEmp);
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postMgrEmp", notes="관리자 사원 등록", response=Integer.class)
	public CommonMessage postMgrEmp(@RequestBody MgrEmpMgVO mgrEmpMgVO) {
		this.parameterLog("MgrEmpMg[postMgrEmp]", mgrEmpMgVO);
		
		CommonMessage output = new CommonMessage();
		
		output.setOk();
		output.setData(0);
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putMgrEmp", notes="관리자 사원 수정", response=Integer.class)
	public CommonMessage putMgrEmp(@RequestBody MgrEmpMgVO mgrEmpMgVO) {
		this.parameterLog("MgrEmpMg[putMgrEmp]", mgrEmpMgVO);
		
		CommonMessage output = new CommonMessage();
		
		output.setOk();
		output.setData(0);
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteMgrEmp", notes="관리자 사원 삭제", response=Integer.class)
	public CommonMessage deleteMgrEmp(@RequestBody MgrEmpMgVO mgrEmpMgVO) {
		this.parameterLog("MgrEmpMg[deleteMgrEmp]", mgrEmpMgVO);
		
		CommonMessage output = new CommonMessage();
		
		output.setOk();
		output.setData(0);
		return output;
	}
	
}

