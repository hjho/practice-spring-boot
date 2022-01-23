package hjho.prj.prct.biz.main.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.mapper.LoginMapper;
import hjho.prj.prct.biz.main.model.LoginPVO;
import hjho.prj.prct.biz.main.model.LoginRVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class LoginService { 
	
	@Autowired
	private LoginMapper loginMapper;
	
	public LoginRVO loginProc(LoginPVO loginPVO) throws UserException {
		LoginRVO returnVO = null;
		
		// 입력 값 검증
		if(this.isParamCheckOk(loginPVO)) {
			
			// ID, PW 검증
			returnVO = this.loginCheck(loginPVO);
			
		} else {
			throw new UserException("9001");
		}
		
		return returnVO;
	}
	private LoginRVO loginCheck(LoginPVO loginPVO) throws UserException {
		LoginRVO returnVO = null;
		
		// ID 검즘
		int idCnt = loginMapper.idCheck(loginPVO);
		if(idCnt == 0) {
			throw new UserException("9100");
		}
		// 비밀번호 검증
		returnVO = loginMapper.loginProc(loginPVO);
		if(StringUtils.isEmpty(returnVO.getUserId())) {
			throw new UserException("9101");
		}
		
		return returnVO;
	}
	
	private boolean isParamCheckOk(LoginPVO loginPVO) throws UserException {
		
		if(loginPVO != null) {
			
			if(StringUtils.isEmpty(loginPVO.getUserId())) {
				throw new UserException("9002", new String[] {"아이디"});
				
			} else if(StringUtils.isEmpty(loginPVO.getUserPw())) {
				throw new UserException("9002", new String[] {"패스워드"});
				
			}
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
}
