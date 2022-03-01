package hjho.prj.prct.biz.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.model.MgrInfoVO;
import hjho.prj.prct.biz.system.mapper.MgrMgMapper;

@Service
public class MgrMgService {
	
	@Autowired 
	private MgrMgMapper mgrMgMapper;

	public boolean tokenSave(MgrInfoVO mgrInfoVO, String refreshToken) {
		
		int saveCnt = mgrMgMapper.tokenSave(mgrInfoVO.getMgrId(), refreshToken);
		
		return saveCnt > 0;
	}
	
}
