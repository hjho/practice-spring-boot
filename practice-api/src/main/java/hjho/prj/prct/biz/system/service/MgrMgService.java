package hjho.prj.prct.biz.system.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.main.model.MgrInfoVO;
import hjho.prj.prct.biz.system.mapper.MgrMgMapper;
import hjho.prj.prct.biz.system.model.MgrMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrMgVO;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.LongUtil;

@Service
public class MgrMgService {
	
	@Autowired 
	private MgrMgMapper mgrMgMapper;

	@Transactional(rollbackFor={DataAccessException.class})
	public boolean tokenSave(MgrInfoVO mgrInfoVO, String refreshToken) {
		
		int saveCnt = mgrMgMapper.tokenSave(mgrInfoVO.getMgrId(), refreshToken);
		
		return saveCnt > 0;
	}
	
	@Transactional(readOnly=true)
	public String getToken(MgrInfoVO mgrInfoVO) {
		
		String refreshTOken = mgrMgMapper.getToken(mgrInfoVO.getMgrId());
		
		return refreshTOken;
	}

	@Transactional(readOnly=true)
	public List<MgrMgPagingRVO> getSysMgr(MgrMgPagingPVO mgrMgPagingPVO) {
		return mgrMgMapper.getSysMgr(mgrMgPagingPVO);
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int postSysMgr(MgrMgVO mgrMgVO) throws UserException {
		int insCnt = 0;
		
		// 관리자 아이디 채번
		mgrMgVO.setMgrId(mgrMgMapper.getSysMgrId());
		
		// 입력값 확인.
		if(this.isInputValOk(mgrMgVO)) {
			// PK 확인.
			if(this.isDataOne(mgrMgVO)) {
				throw new UserException("9005", new String[] {"관리자아이디"});
			} else {
				insCnt = mgrMgMapper.postSysMgr(mgrMgVO);
			}
			
		}
		
		return insCnt;
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int putSysMgr(MgrMgVO mgrMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(mgrMgVO)) {
				updCnt = mgrMgMapper.putSysMgr(mgrMgVO);
			} else {
				throw new UserException("9006", new String[] {"관리자아이디"});
			}
		}
		
		return updCnt;
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteSysMgr(MgrMgVO mgrMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(mgrMgVO)) {
				if("99".equals(mgrMgVO.getSysMgrStatCd())) {
					delCnt = mgrMgMapper.deleteSysMgr(mgrMgVO);
				} else {
					throw new UserException("9202");
				}
			} else {
				throw new UserException("9006", new String[] {"관리자아이디"});
			}
		}
		
		return delCnt;
	}
	// 입력값 확인.
	private boolean isInputValOk(MgrMgVO mgrMgVO) throws UserException {
		
		if(mgrMgVO != null) {
			
			// 관리자아이디
			if(StringUtils.isEmpty(mgrMgVO.getMgrId())) {
				throw new UserException("9002", new String[] {"관리자아이디"});
			// 사원아이디
			} else if(LongUtil.isEmpty(mgrMgVO.getEmpId())) {
				throw new UserException("9002", new String[] {"사원아이디"});
			// 시스템관리자상태코드
			} else if(StringUtils.isEmpty(mgrMgVO.getSysMgrStatCd())) {
				throw new UserException("9002", new String[] {"시스템관리자상태코드"});
			}
			
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(MgrMgVO mgrMgVO) {
		
		int cnt = mgrMgMapper.pkCheck(mgrMgVO);
		
		return (cnt == 1);
	}
	
}
