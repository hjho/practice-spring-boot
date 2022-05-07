package hjho.prj.prct.biz.main.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.mapper.LoginMapper;
import hjho.prj.prct.biz.main.model.LoginPVO;
import hjho.prj.prct.biz.main.model.MgrInfoVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class LoginService { 
	
	@Autowired
	private LoginMapper loginMapper;
	
	public List<MgrInfoVO>  loginProc(LoginPVO loginPVO) throws UserException {
		List<MgrInfoVO>  returnVO = null;
		
		// 입력 값 검증
		if(this.isParamCheckOk(loginPVO)) {
			
			// ID, PW 검증
			returnVO = this.loginCheck(loginPVO);
			
		} else {
			throw new UserException("9001");
		}
		
		return returnVO;
	}
	private List<MgrInfoVO> loginCheck(LoginPVO loginPVO) throws UserException {
		List<MgrInfoVO> grpList = null;
		// ID 검즘.
		int idCnt = loginMapper.idCheck(loginPVO);
		if(idCnt == 0) {
			throw new UserException("9100");
		}
		// 비밀번호 검증.
		int pwCnt = loginMapper.pwCheck(loginPVO);
		if(pwCnt == 0) {
			loginMapper.errPw(loginPVO);
			throw new UserException("9101");
		}
		// 역할 검증.
		grpList = loginMapper.loginProc(loginPVO);
		if(ObjectUtils.isEmpty(grpList)) {
			throw new UserException("9102");
		}
		
		return grpList;
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
