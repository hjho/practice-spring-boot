package hjho.prj.prct.biz.system.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.system.mapper.CodeDtMgMapper;
import hjho.prj.prct.biz.system.model.CodeDtMgPagingPVO;
import hjho.prj.prct.biz.system.model.CodeDtMgPagingRVO;
import hjho.prj.prct.biz.system.model.CodeDtMgVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class CodeDtMgService {
	
	@Autowired 
	private CodeDtMgMapper codeDtMgMapper;
	
	@Transactional(readOnly=true)
	public List<CodeDtMgPagingRVO> getSysCodeDt(CodeDtMgPagingPVO codeDtMgPagingPVO) {
		return codeDtMgMapper.getSysCodeDt(codeDtMgPagingPVO);
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int postSysCodeDt(CodeDtMgVO codeDtMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(codeDtMgVO)) {
			// PK 확인.
			if(this.isDataOne(codeDtMgVO)) {
				throw new UserException("9005", new String[] {"공통코드아이디, 코드값"});
			} else {
				insCnt = codeDtMgMapper.postSysCodeDt(codeDtMgVO);
			}
			
		}
		
		return insCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putSysCodeDt(CodeDtMgVO codeDtMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(codeDtMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(codeDtMgVO)) {
				updCnt = codeDtMgMapper.putSysCodeDt(codeDtMgVO);
			} else {
				throw new UserException("9006", new String[] {"공통코드아이디, 코드값"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class, UserException.class})
	public int putSysCodeDtSort(CodeDtMgVO codeDtMgVO) throws UserException {
		if(codeDtMgVO != null) {
			
			// 공통코드아이디
			if(StringUtils.isEmpty(codeDtMgVO.getCommCdId())) {
				throw new UserException("9002", new String[] {"공통코드아이디"});
				
			} else if(StringUtils.isEmpty(codeDtMgVO.getCdVal())) {
				throw new UserException("9002", new String[] {"코드값"});
				
			} else if(StringUtils.isEmpty(codeDtMgVO.getSortOrd())) {
				throw new UserException("9002", new String[] {"정렬순서"});
			}
			
		} else {
			throw new UserException("9001");
		}

		int updCnt = codeDtMgMapper.putSysCodeDtSort(codeDtMgVO);
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteSysCodeDt(CodeDtMgVO codeDtMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(codeDtMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(codeDtMgVO)) {
				delCnt = codeDtMgMapper.deleteSysCodeDt(codeDtMgVO);
			} else {
				throw new UserException("9006", new String[] {"공통코드아이디, 코드값"});
			}
		}
		
		return delCnt;
	} 
	
	// 입력값 확인.
	private boolean isInputValOk(CodeDtMgVO codeDtMgVO) throws UserException {
		
		if(codeDtMgVO != null) {
			
			// 공통코드아이디
			if(StringUtils.isEmpty(codeDtMgVO.getCommCdId())) {
				throw new UserException("9002", new String[] {"공통코드아이디"});
				
			} else if(StringUtils.isEmpty(codeDtMgVO.getCdVal())) {
				throw new UserException("9002", new String[] {"코드값"});
				
			} else if(StringUtils.isEmpty(codeDtMgVO.getCdValNm())) {
				throw new UserException("9002", new String[] {"코드값명"});
			}
			
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(CodeDtMgVO codeDtMgVO) {
		
		int cnt = codeDtMgMapper.pkCheck(codeDtMgVO);
		
		return (cnt == 1);
	}

}
