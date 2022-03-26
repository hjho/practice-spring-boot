package hjho.prj.prct.biz.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.system.mapper.MgrGrpMgMapper;
import hjho.prj.prct.biz.system.model.MgrGrpMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrGrpMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrGrpMgVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class MgrGrpMgService {
	
	@Autowired 
	private MgrGrpMgMapper mgrGrpMgMapper;

	public List<MgrGrpMgPagingRVO> getSysMgrGrp(MgrGrpMgPagingPVO mgrGrpMgPagingPVO) {
		return mgrGrpMgMapper.getSysMgrGrp(mgrGrpMgPagingPVO);
	}

	public int postSysMgrGrp(MgrGrpMgVO mgrGrpMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrGrpMgVO)) {
			// PK 확인.
			if(this.isDataOne(mgrGrpMgVO)) {
				throw new UserException("9005", new String[] {"관리자그룹아이디"});
			} else {
				insCnt = mgrGrpMgMapper.postSysMgrGrp(mgrGrpMgVO);
			}
			
		}
		
		return insCnt;
	}

	public int putSysMgrGrp(MgrGrpMgVO mgrGrpMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrGrpMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(mgrGrpMgVO)) {
				updCnt = mgrGrpMgMapper.putSysMgrGrp(mgrGrpMgVO);
			} else {
				throw new UserException("9006", new String[] {"관리자그룹아이디"});
			}
		}
		
		return updCnt;
	}

	public int deleteSysMgrGrp(MgrGrpMgVO mgrGrpMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(mgrGrpMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(mgrGrpMgVO)) {
				delCnt = mgrGrpMgMapper.deleteSysMgrGrp(mgrGrpMgVO);
			} else {
				throw new UserException("9006", new String[] {"관리자그룹아이디"});
			}
		}
		
		return delCnt;
	}
	// 입력값 확인.
	private boolean isInputValOk(MgrGrpMgVO mgrGrpMgVO) throws UserException {
		
		return true;
	}
	
	// PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(MgrGrpMgVO mgrGrpMgVO) {
		
		int cnt = mgrGrpMgMapper.pkCheck(mgrGrpMgVO);
		
		return (cnt == 1);
	}
	
}
