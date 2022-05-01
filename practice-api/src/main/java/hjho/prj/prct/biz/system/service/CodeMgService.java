package hjho.prj.prct.biz.system.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.system.mapper.CodeMgMapper;
import hjho.prj.prct.biz.system.model.CodeMgPagingPVO;
import hjho.prj.prct.biz.system.model.CodeMgPagingRVO;
import hjho.prj.prct.biz.system.model.CodeMgVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class CodeMgService {
	
	@Autowired 
	private CodeMgMapper codeMgMapper;
	
	@Transactional(readOnly=true)
	public List<CodeMgPagingRVO> getSysCode(CodeMgPagingPVO codeMgPagingPVO) {
		return codeMgMapper.getSysCode(codeMgPagingPVO);
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int postSysCode(CodeMgVO codeMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(codeMgVO)) {
			// PK 확인.
			if(this.isDataOne(codeMgVO)) {
				throw new UserException("9005", new String[] {"공통코드아이디"});
			} else {
				insCnt = codeMgMapper.postSysCode(codeMgVO);
			}
			
		}
		
		return insCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putSysCode(CodeMgVO codeMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(codeMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(codeMgVO)) {
				updCnt = codeMgMapper.putSysCode(codeMgVO);
			} else {
				throw new UserException("9006", new String[] {"공통코드아이디"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteSysCode(CodeMgVO codeMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(codeMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(codeMgVO)) {
				delCnt = codeMgMapper.deleteSysCode(codeMgVO);
			} else {
				throw new UserException("9006", new String[] {"공통코드아이디"});
			}
		}
		
		return delCnt;
	} 
	
	// 입력값 확인.
	private boolean isInputValOk(CodeMgVO codeMgVO) throws UserException {
		
		if(codeMgVO != null) {
			
			// 공통코드아이디
			if(StringUtils.isEmpty(codeMgVO.getCommCdId())) {
				throw new UserException("9002", new String[] {"공통코드아이디"});
				
			} else if(StringUtils.isEmpty(codeMgVO.getCommCdNm())) {
				throw new UserException("9002", new String[] {"공통코드명"});
			}
			
			if("Y".equals(codeMgVO.getAddtCdUseYn())) {
				if(StringUtils.isEmpty(codeMgVO.getAddtCdNm())) {
					throw new UserException("9002", new String[] {"부속코드를 사용할 경우 부속코드명"});
				}
			}
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(CodeMgVO codeMgVO) {
		
		int cnt = codeMgMapper.pkCheck(codeMgVO);
		
		return (cnt == 1);
	}
	
}
