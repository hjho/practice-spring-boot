package hjho.prj.prct.biz.company.controller;

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

import hjho.prj.prct.biz.company.model.DepartmentsMgPagingPVO;
import hjho.prj.prct.biz.company.model.DepartmentsMgPagingRVO;
import hjho.prj.prct.biz.company.model.DepartmentsMgVO;
import hjho.prj.prct.biz.company.service.DepartmentsMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/company/departments")
@Api(tags="DepartmentsMg", value="자사 부서 관리", description="자사 부서 관리")
public class DepartmentsMgController extends CommonController {
	
	@Autowired
	private DepartmentsMgService departmentsMgService;
	
	@GetMapping()
	@ApiOperation(value="getDepartments", notes="자사 부서 조회", response=DepartmentsMgPagingRVO.class)
	public CommonMessage getDepartments(@ModelAttribute DepartmentsMgPagingPVO departmentsMgPagingPVO) {
		this.parameterLog("DepartmentsMg[getDepartments]", departmentsMgPagingPVO);
		CommonMessage output = new CommonMessage();
		
		List<DepartmentsMgPagingRVO> listMgrDept = departmentsMgService.getDepartments(departmentsMgPagingPVO);
		
		output.setOk();
		output.setData(listMgrDept);
		return output;
	}
	@GetMapping("/box")
	@ApiOperation(value="getDepartmentsBox", notes="자사 부서 조회(selectBox)", response=DepartmentsMgPagingRVO.class)
	public CommonMessage getDepartmentsBox() {
		CommonMessage output = new CommonMessage();
		output.setOk();
		output.setData(departmentsMgService.getDepartmentsBox());
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postDepartments", notes="자사 부서 등록", response=Integer.class)
	public CommonMessage postDepartments(@RequestBody DepartmentsMgVO departmentsMgVO) throws UserException {
		this.parameterLog("DepartmentsMg[postDepartments]", departmentsMgVO);
		CommonMessage output = new CommonMessage();
		
		int insCnt = departmentsMgService.postDepartments(departmentsMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putDepartments", notes="자사 부서 수정", response=Integer.class)
	public CommonMessage putDepartments(@RequestBody DepartmentsMgVO departmentsMgVO) throws UserException {
		this.parameterLog("DepartmentsMg[putDepartments]", departmentsMgVO);
		CommonMessage output = new CommonMessage();
		
		int updCnt = departmentsMgService.putDepartments(departmentsMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteDepartments", notes="자사 부서 삭제", response=Integer.class)
	public CommonMessage deleteDepartments(@RequestBody DepartmentsMgVO departmentsMgVO) throws UserException {
		this.parameterLog("DepartmentsMg[deleteDepartments]", departmentsMgVO);
		CommonMessage output = new CommonMessage();
		
		int delCnt = departmentsMgService.deleteDepartments(departmentsMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PostMapping("/test")
	@ApiOperation(value="test", notes="테스트", response=Integer.class)
	public CommonMessage test(@RequestBody @ApiParam(name="map", value="departmentsMgList") Map<String, List<DepartmentsMgVO>> map) {
		List<DepartmentsMgVO> departmentsMgList = map.get("departmentsMgList");
		
		this.parameterLog("DepartmentsMg[test]", departmentsMgList);
		
		CommonMessage output = new CommonMessage();
		
		output.setOk();
		output.setData(0);
		return output;
	}
	
}

