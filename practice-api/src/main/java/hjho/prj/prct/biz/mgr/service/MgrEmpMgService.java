package hjho.prj.prct.biz.mgr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.mgr.mapper.MgrEmpMgMapper;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgPagingRVO;

@Service
public class MgrEmpMgService {
	
	@Autowired 
	private MgrEmpMgMapper mgrEmpMgMapper;
	
	public List<MgrEmpMgPagingRVO> getMgrEmp(MgrEmpMgPagingPVO mgrDeEmpMgPagingPVO) {
		return mgrEmpMgMapper.getMgrEmp(mgrDeEmpMgPagingPVO);
	} 
	
}
