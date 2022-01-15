package hjho.prj.prct.biz.mgr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.mgr.mapper.MgrJobHsMgMapper;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgVO;
import hjho.prj.prct.biz.mgr.model.MgrJobHsMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrJobHsMgPagingRVO;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.LongUtil;

@Service
public class MgrJobHsMgService {
	
	@Autowired 
	private MgrJobHsMgMapper mgrJobHsMgMapper;
	
	@Transactional(readOnly=true)
	public List<MgrJobHsMgPagingRVO> getMgrJobHs(MgrJobHsMgPagingPVO mgrJobHsMgPagingPVO) {
		if(LongUtil.isEmpty(mgrJobHsMgPagingPVO.getEmployeeId())) {
			return new ArrayList<>();
		}
		return mgrJobHsMgMapper.getMgrJobHs(mgrJobHsMgPagingPVO);
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteMgrJobHs(MgrEmpMgVO mgrEmpMgVO) throws UserException {
		int delCnt = 0;
		
		if(LongUtil.isNotEmpty(mgrEmpMgVO.getEmployeeId())) {
			delCnt = mgrJobHsMgMapper.deleteMgrJobHs(mgrEmpMgVO);
		}
		
		return delCnt;
	}
	
}
