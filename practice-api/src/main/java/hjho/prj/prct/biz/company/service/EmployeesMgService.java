package hjho.prj.prct.biz.company.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.company.mapper.EmployeesMgMapper;
import hjho.prj.prct.biz.company.model.EmployeesMgPagingPVO;
import hjho.prj.prct.biz.company.model.EmployeesMgPagingRVO;
import hjho.prj.prct.biz.company.model.EmployeesMgVO;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.LongUtil;

@Service
public class EmployeesMgService {
	
	@Autowired 
	private EmployeesMgMapper employeesMgMapper;
	
	@Transactional(readOnly=true)
	public List<EmployeesMgPagingRVO> getEmployees(EmployeesMgPagingPVO mgrDeEmpMgPagingPVO) {
		return employeesMgMapper.getEmployees(mgrDeEmpMgPagingPVO);
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int postEmployees(EmployeesMgVO employeesMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(employeesMgVO, false)) {
			// PK 확인.
			if(this.isEmailDataOne(employeesMgVO)) {
				throw new UserException("9005", new String[] {"이메일"});
			} else {
				insCnt = employeesMgMapper.postEmployees(employeesMgVO);
			}
			
		}
		
		return insCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putEmployees(EmployeesMgVO employeesMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(employeesMgVO, true)) {
			
			// PK 확인.
			if(this.isDataOne(employeesMgVO)) {
				
				// JOB_ID, DEPARTMENT_ID 변경 확인. >> JOB_HISTORY TRIGGER
				EmployeesMgVO changeVO = this.getEmpChangeVO(employeesMgVO);
				
				updCnt = employeesMgMapper.putEmployees(changeVO);
			} else {
				throw new UserException("9006", new String[] {"관리자아이디"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteEmployees(EmployeesMgVO employeesMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(employeesMgVO, true)) {
			
			// PK 확인.
			if(this.isDataOne(employeesMgVO)) {
				
				// 삭제 가능 여부 확인.
				if(this.isDelOk(employeesMgVO)) {
					delCnt = employeesMgMapper.deleteEmployees(employeesMgVO);
				} 
			} else {
				throw new UserException("9006", new String[] {"관리자아이디"});
			}
		}
		
		return delCnt;
	} 
	
	// 관리자 관리 입력값 확인.
	private boolean isInputValOk(EmployeesMgVO employeesMgVO, boolean isNotSeq) throws UserException {
		
		if(employeesMgVO != null) {
			
			// 사원아이디
			if(isNotSeq && LongUtil.isEmpty(employeesMgVO.getEmployeeId())) {
				throw new UserException("9002", new String[] {"사원번호"});
				
			// 사원 성
			} else if(StringUtils.isEmpty(employeesMgVO.getLastName())) {
				throw new UserException("9002", new String[] {"부서명"});
			
			// 입사일자
			} else if(StringUtils.isEmpty(employeesMgVO.getHireDate())) {
				throw new UserException("9002", new String[] {"입사일자"});
			
			// 이메일
			} else if(StringUtils.isEmpty(employeesMgVO.getEmail())) {
				throw new UserException("9002", new String[] {"이메일"});
				
			// 직책
			} else if(StringUtils.isEmpty(employeesMgVO.getJobId())) {
				throw new UserException("9002", new String[] {"직책"});
			}
			
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// 관리자 관리 PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(EmployeesMgVO employeesMgVO) {
		
		int cnt = employeesMgMapper.pkCheck(employeesMgVO);
		
		return (cnt == 1);
	}
	
	// 관리자 관리 이메일 중복 확인.
	@Transactional(readOnly=true)
	private boolean isEmailDataOne(EmployeesMgVO employeesMgVO) {
		
		int cnt = employeesMgMapper.emailCheck(employeesMgVO);
		
		return (cnt == 1);
	}
	
	// 사원 삭제 가능 여부 확인.
	@Transactional(readOnly=true)
	private boolean isDelOk(EmployeesMgVO employeesMgVO) throws UserException {
		int cnt = 0;
		
		// Job History Data Check
		cnt = employeesMgMapper.empDelJobHsCheck(employeesMgVO);
		if(cnt > 0) throw new UserException("9201");
		
		return true;
	}
	
	private EmployeesMgVO getEmpChangeVO(EmployeesMgVO employeesMgVO) throws UserException {
		EmployeesMgVO newEmpVO = employeesMgVO;
		EmployeesMgVO oldEmpVO = employeesMgMapper.inqrEmployees(newEmpVO.getEmployeeId());
		
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
