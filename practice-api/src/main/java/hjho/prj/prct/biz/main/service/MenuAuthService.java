package hjho.prj.prct.biz.main.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.mapper.MenuAuthMapper;
import hjho.prj.prct.biz.main.model.MenuAuthPVO;
import hjho.prj.prct.biz.main.model.MenuAuthRVO;
import hjho.prj.prct.biz.main.model.MenuAuthVO;
import hjho.prj.prct.biz.main.model.MgrAuthPVO;
import hjho.prj.prct.biz.main.model.MgrAuthRVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class MenuAuthService { 
	
	@Autowired
	private MenuAuthMapper menuAuthMapper;
	
	public List<MenuAuthRVO> getMainMenuAuth(MenuAuthPVO menuAuthPVO) {
		
		List<MenuAuthRVO> menuHrList = menuAuthMapper.getMenuAuthHr(menuAuthPVO);
		
		for (MenuAuthRVO mainMenuAuthVO : menuHrList) {
			// 상위 메뉴 아이디.
			menuAuthPVO.setMenuId(mainMenuAuthVO.getMenuId());
			List<MenuAuthVO> menuLr = menuAuthMapper.getMenuAuthLr(menuAuthPVO);
			mainMenuAuthVO.setMenuLr(menuLr);
		}
		
		return menuHrList;
	}

	public MgrAuthRVO getMgrAuth(MgrAuthPVO mgrAuthPVO) throws UserException {
		// 필수 입력값 체크 
		if(mgrAuthPVO != null) {
			if(StringUtils.isEmpty(mgrAuthPVO.getMgrId())) {
				throw new UserException("9002", new String[] {"관리자아이디"});
			} else if(StringUtils.isEmpty(mgrAuthPVO.getMgrGrpId())) {
				throw new UserException("9002", new String[] {"관리자그룹아이디"});
			} else if(StringUtils.isEmpty(mgrAuthPVO.getPageUrl())) {
				throw new UserException("9002", new String[] {"페이지URL"});
			} else if(StringUtils.isEmpty(mgrAuthPVO.getMethod())) {
				throw new UserException("9002", new String[] {"권한유형"});
			}
		} else {
			throw new UserException("9001");
		}
		
		MgrAuthRVO mgrAuthRVO = menuAuthMapper.getMgrAuth(mgrAuthPVO);
		
		return mgrAuthRVO;
	}
	
}
