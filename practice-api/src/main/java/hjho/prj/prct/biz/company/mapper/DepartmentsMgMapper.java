package hjho.prj.prct.biz.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.company.model.DepartmentsMgPagingPVO;
import hjho.prj.prct.biz.company.model.DepartmentsMgPagingRVO;
import hjho.prj.prct.biz.company.model.DepartmentsMgVO;

@Mapper
public interface DepartmentsMgMapper {

	List<DepartmentsMgPagingRVO> getDepartments(DepartmentsMgPagingPVO departmentsMgPagingPVO);

	List<DepartmentsMgPagingRVO> getDepartmentsBox();
	
	int postDepartments(DepartmentsMgVO departmentsMgVO);

	int putDepartments(DepartmentsMgVO departmentsMgVO);

	int deleteDepartments(DepartmentsMgVO departmentsMgVO);

	int pkCheck(DepartmentsMgVO departmentsMgVO);

}
