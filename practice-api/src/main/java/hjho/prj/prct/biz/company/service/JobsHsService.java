package hjho.prj.prct.biz.company.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.company.mapper.JobsHsMapper;
import hjho.prj.prct.biz.company.model.EmployeesMgVO;
import hjho.prj.prct.biz.company.model.JobsHsPagingPVO;
import hjho.prj.prct.biz.company.model.JobsHsPagingRVO;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.LongUtil;

@Service
public class JobsHsService {
	
	@Autowired 
	private JobsHsMapper jobsHsMapper;
	
	@Transactional(readOnly=true)
	public List<JobsHsPagingRVO> getJobsHs(JobsHsPagingPVO jobsHsPagingPVO) {
		if(LongUtil.isEmpty(jobsHsPagingPVO.getEmployeeId())) {
			return new ArrayList<>();
		}
		return jobsHsMapper.getJobsHs(jobsHsPagingPVO);
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteJobsHs(EmployeesMgVO employeesMgVO) throws UserException {
		int delCnt = 0;
		
		if(LongUtil.isNotEmpty(employeesMgVO.getEmployeeId())) {
			delCnt = jobsHsMapper.deleteJobsHs(employeesMgVO);
		}
		
		return delCnt;
	}
	
}
