package hjho.prj.prct.biz.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.system.mapper.MgrDeptMgMapper;
import hjho.prj.prct.biz.system.model.MgrDeptMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrDeptMgPagingRVO;

@Service
public class MgrDeptMgService {
	
	@Autowired 
	private MgrDeptMgMapper mgrDeptMgMapper;
	
	public List<MgrDeptMgPagingRVO> getMgrDept(MgrDeptMgPagingPVO mgrDeptMgPagingPVO) {
		
		return mgrDeptMgMapper.getMgrDept(mgrDeptMgPagingPVO);
	} 
	
}
