package hjho.prj.prct.biz.system.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.system.mapper.MgrRoleMgMapper;
import hjho.prj.prct.biz.system.model.MgrMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrRoleMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrRoleMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrRoleMgVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class MgrRoleMgService {
	
	@Autowired 
	private MgrRoleMgMapper mgrRoleMgMapper;

	@Transactional(readOnly=true)
	public List<MgrRoleMgPagingRVO> getSysMgrRole(MgrRoleMgPagingPVO mgrRoleMgPagingPVO) {
		return mgrRoleMgMapper.getSysMgrRole(mgrRoleMgPagingPVO);
	}
	
	@Transactional(readOnly=true)
	public List<MgrMgPagingRVO> getSysMgrRoleMgr(MgrRoleMgPagingPVO mgrRoleMgPagingPVO) {
		return mgrRoleMgMapper.getSysMgrRoleMgr(mgrRoleMgPagingPVO);
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int postSysMgrRole(MgrRoleMgVO mgrRoleMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrRoleMgVO)) {
			// PK 확인.
			if(this.isDataOne(mgrRoleMgVO)) {
				throw new UserException("9005", new String[] {"관리자아이디"});
			} else {
				insCnt = mgrRoleMgMapper.postSysMgrRole(mgrRoleMgVO);
			}
			
		}
		
		return insCnt;
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int putSysMgrRole(MgrRoleMgVO mgrRoleMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrRoleMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(mgrRoleMgVO)) {
				updCnt = mgrRoleMgMapper.putSysMgrRole(mgrRoleMgVO);
			} else {
				throw new UserException("9006", new String[] {"관리자아이디"});
			}
		}
		
		return updCnt;
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteSysMgrRole(MgrRoleMgVO mgrRoleMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrRoleMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(mgrRoleMgVO)) {
				delCnt = mgrRoleMgMapper.deleteSysMgrRole(mgrRoleMgVO);
			} else {
				throw new UserException("9006", new String[] {"관리자아이디"});
			}
		}
		
		return delCnt;
	}
	// 입력값 확인.
	private boolean isInputValOk(MgrRoleMgVO mgrRoleMgVO) throws UserException {
		
		if(mgrRoleMgVO != null) {
			
			// 관리자그룹아이디
			if(StringUtils.isEmpty(mgrRoleMgVO.getMgrId())) {
				throw new UserException("9002", new String[] {"관리자그룹아이디"});
				
			// 관리자아이디
			} else if(StringUtils.isEmpty(mgrRoleMgVO.getMgrId())) {
				throw new UserException("9002", new String[] {"관리자아이디"});
			}
			
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(MgrRoleMgVO mgrRoleMgVO) {
		
		int cnt = mgrRoleMgMapper.pkCheck(mgrRoleMgVO);
		
		return (cnt == 1);
	}

}
