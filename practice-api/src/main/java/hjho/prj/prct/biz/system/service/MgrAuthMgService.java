package hjho.prj.prct.biz.system.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.system.mapper.MgrAuthMgMapper;
import hjho.prj.prct.biz.system.model.MgrAuthMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrAuthMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrAuthMgVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class MgrAuthMgService {
	
	@Autowired 
	private MgrAuthMgMapper mgrAuthMgMapper;

	@Transactional(readOnly=true)
	public List<MgrAuthMgPagingRVO> getSysMgrAuth(MgrAuthMgPagingPVO mgrAuthMgPagingPVO) {
		return mgrAuthMgMapper.getSysMgrAuth(mgrAuthMgPagingPVO);
	}
	
	@Transactional(readOnly=true)
	public List<MgrAuthMgPagingRVO> getSysMgrAuthMenu(MgrAuthMgPagingPVO mgrAuthMgPagingPVO) {
		return mgrAuthMgMapper.getSysMgrAuthMenu(mgrAuthMgPagingPVO);
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int postSysMgrAuth(MgrAuthMgVO mgrAuthMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrAuthMgVO)) {
			// PK 확인.
			if(this.isDataOne(mgrAuthMgVO)) {
				throw new UserException("9005", new String[] {"관리자아이디"});
			} else {
				insCnt = mgrAuthMgMapper.postSysMgrAuth(mgrAuthMgVO);
			}
			
		}
		
		return insCnt;
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int putSysMgrAuth(MgrAuthMgVO mgrAuthMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrAuthMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(mgrAuthMgVO)) {
				updCnt = mgrAuthMgMapper.putSysMgrAuth(mgrAuthMgVO);
			} else {
				throw new UserException("9006", new String[] {"관리자아이디"});
			}
		}
		
		return updCnt;
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteSysMgrAuth(MgrAuthMgVO mgrAuthMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrAuthMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(mgrAuthMgVO)) {
				delCnt = mgrAuthMgMapper.deleteSysMgrAuth(mgrAuthMgVO);
			} else {
				throw new UserException("9006", new String[] {"관리자아이디"});
			}
		}
		
		return delCnt;
	}
	// 입력값 확인.
	private boolean isInputValOk(MgrAuthMgVO mgrAuthMgVO) throws UserException {
		
		if(mgrAuthMgVO != null) {
			
			// 관리자그룹아이디
			if(StringUtils.isEmpty(mgrAuthMgVO.getMgrGrpId())) {
				throw new UserException("9002", new String[] {"관리자그룹아이디"});
				
			// 관리자아이디
			} else if(StringUtils.isEmpty(mgrAuthMgVO.getMenuId())) {
				throw new UserException("9002", new String[] {"메뉴아이디"});
			}
			
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(MgrAuthMgVO mgrAuthMgVO) {
		
		int cnt = mgrAuthMgMapper.pkCheck(mgrAuthMgVO);
		
		return (cnt == 1);
	}

}
