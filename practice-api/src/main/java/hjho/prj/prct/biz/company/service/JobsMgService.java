package hjho.prj.prct.biz.company.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.company.mapper.JobsMgMapper;
import hjho.prj.prct.biz.company.model.JobsMgPagingPVO;
import hjho.prj.prct.biz.company.model.JobsMgPagingRVO;
import hjho.prj.prct.biz.company.model.JobsMgVO;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.LongUtil;

@Service
public class JobsMgService {
	
	@Autowired 
	private JobsMgMapper jobsMgMapper;
	
	@Transactional(readOnly=true)
	public List<JobsMgPagingRVO> getJobs(JobsMgPagingPVO jobsMgPagingPVO) {
		
		return jobsMgMapper.getJobs(jobsMgPagingPVO);
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int postJobs(JobsMgVO jobsMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(jobsMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(jobsMgVO)) {
				throw new UserException("9005", new String[] {"직책아이디"});
			} else {
				insCnt = jobsMgMapper.postJobs(jobsMgVO);
			}
			
		}
		
		return insCnt;
	} 
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putJobs(JobsMgVO jobsMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(jobsMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(jobsMgVO)) {
				updCnt = jobsMgMapper.putJobs(jobsMgVO);
			} else {
				throw new UserException("9006", new String[] {"직책아이디"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteJobs(JobsMgVO jobsMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(jobsMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(jobsMgVO)) {
				delCnt = jobsMgMapper.deleteJobs(jobsMgVO);
			} else {
				throw new UserException("9006", new String[] {"직책아이디"});
			}
		}
		
		return delCnt;
	}

	
	// 관리자 직책 관리 입력값 확인.
	private boolean isInputValOk(JobsMgVO jobsMgVO) throws UserException {
		
		if(jobsMgVO != null) {
			
			// 직책아이디 
			if(StringUtils.isEmpty(jobsMgVO.getJobId())) {
				throw new UserException("9002", new String[] {"직책아이디"});
				
			// 직책명
			} else if(StringUtils.isEmpty(jobsMgVO.getJobTitle())) {
				throw new UserException("9002", new String[] {"직책명"});
			}
			
			// 직책 금액 크기 비교
			long min = LongUtil.isEmpty(jobsMgVO.getMinSalary()) ? 0 : jobsMgVO.getMinSalary();
			long max = LongUtil.isEmpty(jobsMgVO.getMaxSalary()) ? 0 : jobsMgVO.getMaxSalary();
			if(min > max) {
				throw new UserException("9200");
			}
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// 관리자 직책 관리 PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(JobsMgVO jobsMgVO) {
		
		int cnt = jobsMgMapper.pkCheck(jobsMgVO);;
		
		return (cnt == 1);
	}
	
}
