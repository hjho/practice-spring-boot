package hjho.prj.prct.biz.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.company.model.EmployeesMgVO;
import hjho.prj.prct.biz.company.model.JobsHsPagingPVO;
import hjho.prj.prct.biz.company.model.JobsHsPagingRVO;

@Mapper
public interface JobsHsMapper {

	List<JobsHsPagingRVO> getJobsHs(JobsHsPagingPVO jobsHsPagingPVO);

	int deleteJobsHs(EmployeesMgVO employeesMgVO);

	int pkCheck(EmployeesMgVO employeesMgVO);

}
