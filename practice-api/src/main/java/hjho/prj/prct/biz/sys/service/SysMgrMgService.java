package hjho.prj.prct.biz.sys.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.main.model.MgrInfoVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class SysMgrMgService {
	
	@Transactional(rollbackFor={DataAccessException.class})
	public boolean tokenSave(MgrInfoVO mgrInfoVO, String refreshToken) throws UserException {
		
		int saveCnt = 1;
		
		return saveCnt == 1;
	} 
}
