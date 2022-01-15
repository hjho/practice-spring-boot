package hjho.prj.prct.biz.mgr.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.mgr.mapper.MgrJobMgMapper;
import hjho.prj.prct.biz.mgr.model.MgrJobMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrJobMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrJobMgVO;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.LongUtil;

@Service
public class MgrJobMgService {
	
	@Autowired 
	private MgrJobMgMapper mgrJobMgMapper;
	
	@Transactional(readOnly=true)
	public List<MgrJobMgPagingRVO> getMgrJob(MgrJobMgPagingPVO mgrJobMgPagingPVO) {
		
		return mgrJobMgMapper.getMgrJob(mgrJobMgPagingPVO);
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int postMgrJob(MgrJobMgVO mgrJobMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrJobMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(mgrJobMgVO)) {
				throw new UserException("9005", new String[] {"직책아이디"});
			} else {
				insCnt = mgrJobMgMapper.postMgrJob(mgrJobMgVO);
			}
			
		}
		
		return insCnt;
	} 
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putMgrJob(MgrJobMgVO mgrJobMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrJobMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(mgrJobMgVO)) {
				updCnt = mgrJobMgMapper.putMgrJob(mgrJobMgVO);
			} else {
				throw new UserException("9006", new String[] {"직책아이디"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteMgrJob(MgrJobMgVO mgrJobMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrJobMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(mgrJobMgVO)) {
				delCnt = mgrJobMgMapper.deleteMgrJob(mgrJobMgVO);
			} else {
				throw new UserException("9006", new String[] {"직책아이디"});
			}
		}
		
		return delCnt;
	}

	
	// 관리자 직책 관리 입력값 확인.
	private boolean isInputValOk(MgrJobMgVO mgrJobMgVO) throws UserException {
		
		if(mgrJobMgVO != null) {
			
			// 직책아이디 
			if(StringUtils.isEmpty(mgrJobMgVO.getJobId())) {
				throw new UserException("9002", new String[] {"직책아이디"});
				
			// 직책명
			} else if(StringUtils.isEmpty(mgrJobMgVO.getJobTitle())) {
				throw new UserException("9002", new String[] {"직책명"});
			}
			
			// 직책 금액 크기 비교
			long min = LongUtil.isEmpty(mgrJobMgVO.getMinSalary()) ? 0 : mgrJobMgVO.getMinSalary();
			long max = LongUtil.isEmpty(mgrJobMgVO.getMaxSalary()) ? 0 : mgrJobMgVO.getMaxSalary();
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
	private boolean isDataOne(MgrJobMgVO mgrJobMgVO) {
		
		int cnt = mgrJobMgMapper.pkCheck(mgrJobMgVO);;
		
		return (cnt == 1);
	}
	
}
