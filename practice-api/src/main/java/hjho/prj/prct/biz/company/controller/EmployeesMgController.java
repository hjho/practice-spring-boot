package hjho.prj.prct.biz.company.controller;

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

import hjho.prj.prct.biz.company.model.EmployeesMgPagingPVO;
import hjho.prj.prct.biz.company.model.EmployeesMgPagingRVO;
import hjho.prj.prct.biz.company.model.EmployeesMgVO;
import hjho.prj.prct.biz.company.service.EmployeesMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/company/employees")
@Api(tags="EmployeesMg", value="자사 직원 관리", description="자사 직원 관리")
public class EmployeesMgController extends CommonController {
	
	@Autowired
	private EmployeesMgService employeesMgService;
	
	@GetMapping()
	@ApiOperation(value="getEmployees", notes="자사 직원 조회", response=EmployeesMgPagingRVO.class)
	public CommonMessage getEmployees(@ModelAttribute EmployeesMgPagingPVO employeesMgPagingPVO) {
		this.parameterLog("EmployeesMg[getEmployees]", employeesMgPagingPVO);
		
		CommonMessage output = new CommonMessage();
		
		List<EmployeesMgPagingRVO> listEmployees = employeesMgService.getEmployees(employeesMgPagingPVO);
		
		output.setOk();
		output.setData(listEmployees);
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postEmployees", notes="자사 직원 등록", response=Integer.class)
	public CommonMessage postEmployees(@RequestBody EmployeesMgVO employeesMgVO) throws UserException {
		this.parameterLog("EmployeesMg[postEmployees]", employeesMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int insCnt = employeesMgService.postEmployees(employeesMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putEmployees", notes="자사 직원 수정", response=Integer.class)
	public CommonMessage putEmployees(@RequestBody EmployeesMgVO employeesMgVO) throws UserException {
		this.parameterLog("EmployeesMg[putEmployees]", employeesMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int updCnt = employeesMgService.putEmployees(employeesMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteEmployees", notes="자사 직원 삭제", response=Integer.class)
	public CommonMessage deleteEmployees(@RequestBody EmployeesMgVO employeesMgVO) throws UserException {
		this.parameterLog("EmployeesMg[deleteEmployees]", employeesMgVO);
		
		CommonMessage output = new CommonMessage();
		
		int delCnt = employeesMgService.deleteEmployees(employeesMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}

