package hjho.prj.prct.biz.mgr.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.mgr.mapper.MgrEmpMgMapper;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgVO;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.LongUtil;

@Service
public class MgrEmpMgService {
	
	@Autowired 
	private MgrEmpMgMapper mgrEmpMgMapper;
	
	@Transactional(readOnly=true)
	public List<MgrEmpMgPagingRVO> getMgrEmp(MgrEmpMgPagingPVO mgrDeEmpMgPagingPVO) {
		return mgrEmpMgMapper.getMgrEmp(mgrDeEmpMgPagingPVO);
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int postMgrEmp(MgrEmpMgVO mgrEmpMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrEmpMgVO, false)) {
			// PK 확인.
			if(this.isEmailDataOne(mgrEmpMgVO)) {
				throw new UserException("9005", new String[] {"이메일"});
			} else {
				insCnt = mgrEmpMgMapper.postMgrEmp(mgrEmpMgVO);
			}
			
		}
		
		return insCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putMgrEmp(MgrEmpMgVO mgrEmpMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrEmpMgVO, true)) {
			
			// PK 확인.
			if(this.isDataOne(mgrEmpMgVO)) {
				
				// JOB_ID, DEPARTMENT_ID 변경 확인. >> JOB_HISTORY TRIGGER
				MgrEmpMgVO changeVO = this.getEmpChangeVO(mgrEmpMgVO);
				
				updCnt = mgrEmpMgMapper.putMgrEmp(changeVO);
			} else {
				throw new UserException("9006", new String[] {"관리자아이디"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteMgrEmp(MgrEmpMgVO mgrEmpMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrEmpMgVO, true)) {
			
			// PK 확인.
			if(this.isDataOne(mgrEmpMgVO)) {
				
				// 삭제 가능 여부 확인.
				if(this.isDelOk(mgrEmpMgVO)) {
					delCnt = mgrEmpMgMapper.deleteMgrEmp(mgrEmpMgVO);
				} 
			} else {
				throw new UserException("9006", new String[] {"관리자아이디"});
			}
		}
		
		return delCnt;
	} 
	
	// 관리자 관리 입력값 확인.
	private boolean isInputValOk(MgrEmpMgVO mgrEmpMgVO, boolean isNotSeq) throws UserException {
		
		if(mgrEmpMgVO != null) {
			
			// 사원아이디
			if(isNotSeq && LongUtil.isEmpty(mgrEmpMgVO.getEmployeeId())) {
				throw new UserException("9002", new String[] {"사원번호"});
				
			// 사원 성
			} else if(StringUtils.isEmpty(mgrEmpMgVO.getLastName())) {
				throw new UserException("9002", new String[] {"부서명"});
			
			// 입사일자
			} else if(StringUtils.isEmpty(mgrEmpMgVO.getHireDate())) {
				throw new UserException("9002", new String[] {"입사일자"});
			
			// 이메일
			} else if(StringUtils.isEmpty(mgrEmpMgVO.getEmail())) {
				throw new UserException("9002", new String[] {"이메일"});
				
			// 직책
			} else if(StringUtils.isEmpty(mgrEmpMgVO.getJobId())) {
				throw new UserException("9002", new String[] {"직책"});
			}
			
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// 관리자 관리 PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(MgrEmpMgVO mgrEmpMgVO) {
		
		int cnt = mgrEmpMgMapper.pkCheck(mgrEmpMgVO);
		
		return (cnt == 1);
	}
	
	// 관리자 관리 이메일 중복 확인.
	@Transactional(readOnly=true)
	private boolean isEmailDataOne(MgrEmpMgVO mgrEmpMgVO) {
		
		int cnt = mgrEmpMgMapper.emailCheck(mgrEmpMgVO);
		
		return (cnt == 1);
	}
	
	// 사원 삭제 가능 여부 확인.
	@Transactional(readOnly=true)
	private boolean isDelOk(MgrEmpMgVO mgrEmpMgVO) throws UserException {
		int cnt = 0;
		
		// Job History Data Check
		cnt = mgrEmpMgMapper.empDelJobHsCheck(mgrEmpMgVO);
		if(cnt > 0) throw new UserException("9201");
		
		return true;
	}
	
	private MgrEmpMgVO getEmpChangeVO(MgrEmpMgVO mgrEmpMgVO) throws UserException {
		MgrEmpMgVO newEmpVO = mgrEmpMgVO;
		MgrEmpMgVO oldEmpVO = mgrEmpMgMapper.inrqMgrEmp(newEmpVO.getEmployeeId());
		
		// 직책 변경.
		if(StringUtils.isNotEmpty(newEmpVO.getJobId())) {
			if(newEmpVO.getJobId().equals(oldEmpVO.getJobId())) {
				newEmpVO.setJobId(null);
			}
		}
		// 부서 변경
		if(LongUtil.isNotEmpty(newEmpVO.getDepartmentId())) {
			if(newEmpVO.getDepartmentId().equals(oldEmpVO.getDepartmentId())) {
				newEmpVO.setDepartmentId(null);
			}
		}
		
		if(StringUtils.isNotEmpty(newEmpVO.getEmail())) {
			// 이메일 변경 시 중복 확인.
			if(!newEmpVO.getEmail().equals(oldEmpVO.getEmail())) {
				if(this.isEmailDataOne(newEmpVO)) {
					throw new UserException("9005", new String[] {"이메일"});
				}
			}
		}
		
		return newEmpVO;
	}
}
