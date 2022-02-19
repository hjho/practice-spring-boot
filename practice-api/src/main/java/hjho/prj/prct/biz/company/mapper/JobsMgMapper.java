package hjho.prj.prct.biz.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.company.model.JobsMgPagingPVO;
import hjho.prj.prct.biz.company.model.JobsMgPagingRVO;
import hjho.prj.prct.biz.company.model.JobsMgVO;

@Mapper
public interface JobsMgMapper {

	List<JobsMgPagingRVO> getJobs(JobsMgPagingPVO jobsMgPagingPVO);

	int postJobs(JobsMgVO jobsMgVO);

	int putJobs(JobsMgVO jobsMgVO);

	int deleteJobs(JobsMgVO jobsMgVO);

	int pkCheck(JobsMgVO jobsMgVO);

}
