package hjho.prj.prct.biz.mgr.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.mgr.mapper.MgrDeptMgMapper;
import hjho.prj.prct.biz.mgr.model.MgrDeptMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrDeptMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrDeptMgVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class MgrDeptMgService {
	
	@Autowired 
	private MgrDeptMgMapper mgrDeptMgMapper;
	
	@Transactional(readOnly=true)
	public List<MgrDeptMgPagingRVO> getMgrDept(MgrDeptMgPagingPVO mgrDeptMgPagingPVO) {
		
		return mgrDeptMgMapper.getMgrDept(mgrDeptMgPagingPVO);
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int postMgrDept(MgrDeptMgVO mgrDeptMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrDeptMgVO, false)) {
			
			// PK 확인.
			if(this.isDataZero(mgrDeptMgVO)) {
				insCnt = mgrDeptMgMapper.postMgrDept(mgrDeptMgVO);
			} else {
				throw new UserException("9005", new String[] {"부서번호"});
			}
		}
		
		return insCnt;
	} 
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putMgrDept(MgrDeptMgVO mgrDeptMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrDeptMgVO, true)) {
			
			// PK 확인.
			if(this.isDataZero(mgrDeptMgVO)) {
				throw new UserException("9006", new String[] {"부서번호"});
			} else {
				updCnt = mgrDeptMgMapper.putMgrDept(mgrDeptMgVO);
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteMgrDept(MgrDeptMgVO mgrDeptMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrDeptMgVO, true)) {
			
			// PK 확인.
			if(this.isDataZero(mgrDeptMgVO)) {
				throw new UserException("9006", new String[] {"부서번호"});
			} else {
				delCnt = mgrDeptMgMapper.deleteMgrDept(mgrDeptMgVO);
			}
		}
		
		return delCnt;
	}

	
	// 관리자 관리 입력값 확인.
	private boolean isInputValOk(MgrDeptMgVO mgrDeptMgVO, boolean isNotSeq) throws UserException {
		
		if(mgrDeptMgVO != null) {
			
			// 부서 ID 
			if(isNotSeq && mgrDeptMgVO.getDepartmentId() == null) {
				throw new UserException("9002", new String[] {"부서번호"});
				
			// 부서 명
			} else if(StringUtils.isEmpty(mgrDeptMgVO.getDepartmentName())) {
				throw new UserException("9002", new String[] {"부서명"});
			}
			
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// 관리자 관리 PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataZero(MgrDeptMgVO mgrDeptMgVO) {
		
		int cnt = mgrDeptMgMapper.pkCheck(mgrDeptMgVO);;
		
		return (cnt == 0);
	}
	
}
