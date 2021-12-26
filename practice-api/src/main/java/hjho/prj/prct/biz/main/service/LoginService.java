package hjho.prj.prct.biz.main.service;

import org.apache.commons.lang3.ObjectUtils;
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
			returnVO = loginMapper.loginProc(loginPVO);
			
		} else {
			returnVO = new LoginRVO();
		}
		return returnVO;
	}
	
	
	private boolean isParamCheckOk(LoginPVO loginPVO) throws UserException {
		
		if(ObjectUtils.isEmpty(loginPVO)) {
			throw new UserException("9001");
			
		} else {
			if(StringUtils.isEmpty(loginPVO.getUserId())) {
				throw new UserException("9002", new String[] {"아이디"});
				
			} else if(StringUtils.isEmpty(loginPVO.getUserPw())) {
				throw new UserException("9002", new String[] {"패스워드"});
				
			}
		}
		
		// 값 확인 (테스트용)
		if(!"hjho".equals(loginPVO.getUserId())) {
			throw new UserException("9100");
			
		} else if(!"1234".equals(loginPVO.getUserPw())) {
			throw new UserException("9101");
		}
		
		return true;
	}
	
}
