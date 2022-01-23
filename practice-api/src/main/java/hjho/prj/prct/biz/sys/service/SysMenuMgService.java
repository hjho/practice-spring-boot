package hjho.prj.prct.biz.sys.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.sys.mapper.SysMenuMgMapper;
import hjho.prj.prct.biz.sys.model.SysMenuMgPagingPVO;
import hjho.prj.prct.biz.sys.model.SysMenuMgPagingRVO;
import hjho.prj.prct.biz.sys.model.SysMenuMgVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class SysMenuMgService {
	
	@Autowired 
	private SysMenuMgMapper sysMenuMgMapper;
	
	@Transactional(readOnly=true)
	public List<SysMenuMgPagingRVO> getSysMenu(SysMenuMgPagingPVO sysMenuMgPagingPVO) {
		return sysMenuMgMapper.getSysMenu(sysMenuMgPagingPVO);
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int postSysMenu(SysMenuMgVO sysMenuMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(sysMenuMgVO)) {
			// PK 확인.
			if(this.isDataOne(sysMenuMgVO)) {
				throw new UserException("9005", new String[] {"메뉴아이디"});
			} else {
				insCnt = sysMenuMgMapper.postSysMenu(sysMenuMgVO);
			}
			
		}
		
		return insCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putSysMenu(SysMenuMgVO sysMenuMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(sysMenuMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(sysMenuMgVO)) {
				updCnt = sysMenuMgMapper.putSysMenu(sysMenuMgVO);
			} else {
				throw new UserException("9006", new String[] {"메뉴아이디"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteSysMenu(SysMenuMgVO sysMenuMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(sysMenuMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(sysMenuMgVO)) {
				delCnt = sysMenuMgMapper.deleteSysMenu(sysMenuMgVO);
			} else {
				throw new UserException("9006", new String[] {"메뉴아이디"});
			}
		}
		
		return delCnt;
	} 
	
	// 입력값 확인.
	private boolean isInputValOk(SysMenuMgVO sysMenuMgVO) throws UserException {
		
		if(sysMenuMgVO != null) {
			
			// 사원아이디
			if(StringUtils.isEmpty(sysMenuMgVO.getMenuId())) {
				throw new UserException("9002", new String[] {"메뉴아이디"});
			} 
			
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(SysMenuMgVO sysMenuMgVO) {
		
		int cnt = sysMenuMgMapper.pkCheck(sysMenuMgVO);
		
		return (cnt == 1);
	}
	
}
