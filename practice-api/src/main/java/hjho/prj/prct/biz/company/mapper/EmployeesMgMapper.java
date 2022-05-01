package hjho.prj.prct.biz.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.company.model.EmployeesMgPagingPVO;
import hjho.prj.prct.biz.company.model.EmployeesMgPagingRVO;
import hjho.prj.prct.biz.company.model.EmployeesMgVO;

@Mapper
public interface EmployeesMgMapper {

	List<EmployeesMgPagingRVO> getEmployees(EmployeesMgPagingPVO mgrDeEmpMgPagingPVO);

	int postEmployees(EmployeesMgVO employeesMgVO);

	int putEmployees(EmployeesMgVO employeesMgVO);

	int deleteEmployees(EmployeesMgVO employeesMgVO);

	int pkCheck(EmployeesMgVO employeesMgVO);
	
	int emailCheck(EmployeesMgVO employeesMgVO);

	EmployeesMgVO inqrEmployees(Long employeeId);

	int empDelJobHsCheck(EmployeesMgVO employeesMgVO);

}
