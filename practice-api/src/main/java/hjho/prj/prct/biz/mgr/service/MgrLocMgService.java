package hjho.prj.prct.biz.mgr.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.mgr.mapper.MgrLocMgMapper;
import hjho.prj.prct.biz.mgr.model.MgrLocMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrLocMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrLocMgVO;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.LongUtil;

@Service
public class MgrLocMgService {
	
	@Autowired 
	private MgrLocMgMapper mgrLocMgMapper;

	@Transactional(readOnly=true)
	public List<MgrLocMgPagingRVO> getMgrLoc(MgrLocMgPagingPVO mgrLocMgPagingPVO) {
		return mgrLocMgMapper.getMgrLoc(mgrLocMgPagingPVO);
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int postMgrLoc(MgrLocMgVO mgrLocMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrLocMgVO, false)) {
			
			insCnt = mgrLocMgMapper.postMgrLoc(mgrLocMgVO);
			
		}
		
		return insCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putMgrLoc(MgrLocMgVO mgrLocMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrLocMgVO, true)) {
			
			// PK 확인.
			if(this.isDataOne(mgrLocMgVO)) {
				updCnt = mgrLocMgMapper.putMgrLoc(mgrLocMgVO);
			} else {
				throw new UserException("9006", new String[] {"위치번호"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteMgrLoc(MgrLocMgVO mgrLocMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrLocMgVO, true)) {
			
			// PK 확인.
			if(this.isDataOne(mgrLocMgVO)) {
				delCnt = mgrLocMgMapper.deleteMgrLoc(mgrLocMgVO);
			} else {
				throw new UserException("9006", new String[] {"위치번호"});
			}
		}
		
		return delCnt;
	} 
	
	// 위치 관리 입력값 확인.
	private boolean isInputValOk(MgrLocMgVO mgrLocMgVO, boolean isNotSeq) throws UserException {
		
		if(mgrLocMgVO != null) {
			
			// 위치 ID 
			if(isNotSeq && LongUtil.isEmpty(mgrLocMgVO.getLocationId())) {
				throw new UserException("9002", new String[] {"위치번호"});
				
			// 도시 명
			} else if(StringUtils.isEmpty(mgrLocMgVO.getCity())) {
				throw new UserException("9002", new String[] {"도시명"});
			}
			
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// 관리자 관리 PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(MgrLocMgVO mgrLocMgVO) {
		
		int cnt = mgrLocMgMapper.pkCheck(mgrLocMgVO);;
		
		return (cnt == 1);
	}
}
