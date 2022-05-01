package hjho.prj.prct.biz.system.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.system.mapper.MenuMgMapper;
import hjho.prj.prct.biz.system.model.MenuMgPagingPVO;
import hjho.prj.prct.biz.system.model.MenuMgPagingRVO;
import hjho.prj.prct.biz.system.model.MenuMgVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class MenuMgService {
	
	@Autowired 
	private MenuMgMapper menuMgMapper;
	
	@Transactional(readOnly=true)
	public List<MenuMgPagingRVO> getSysMenu(MenuMgPagingPVO menuMgPagingPVO) {
		return menuMgMapper.getSysMenu(menuMgPagingPVO);
	}

	@Transactional(rollbackFor={DataAccessException.class})
	public int postSysMenu(MenuMgVO menuMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(menuMgVO)) {
			// PK 확인.
			if(this.isDataOne(menuMgVO)) {
				throw new UserException("9005", new String[] {"메뉴아이디"});
			} else {
				insCnt = menuMgMapper.postSysMenu(menuMgVO);
			}
			
		}
		
		return insCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putSysMenu(MenuMgVO menuMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(menuMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(menuMgVO)) {
				updCnt = menuMgMapper.putSysMenu(menuMgVO);
			} else {
				throw new UserException("9006", new String[] {"메뉴아이디"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteSysMenu(MenuMgVO menuMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(menuMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(menuMgVO)) {
				delCnt = menuMgMapper.deleteSysMenu(menuMgVO);
			} else {
				throw new UserException("9006", new String[] {"메뉴아이디"});
			}
		}
		
		return delCnt;
	} 
	
	// 입력값 확인.
	private boolean isInputValOk(MenuMgVO menuMgVO) throws UserException {
		
		if(menuMgVO != null) {
			
			// 사원아이디
			if(StringUtils.isEmpty(menuMgVO.getMenuId())) {
				throw new UserException("9002", new String[] {"메뉴아이디"});
			} 
			
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(MenuMgVO menuMgVO) {
		
		int cnt = menuMgMapper.pkCheck(menuMgVO);
		
		return (cnt == 1);
	}
	
}
