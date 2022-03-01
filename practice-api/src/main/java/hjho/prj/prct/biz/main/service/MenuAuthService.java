package hjho.prj.prct.biz.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.mapper.MenuAuthMapper;
import hjho.prj.prct.biz.main.model.MenuAuthPVO;
import hjho.prj.prct.biz.main.model.MenuAuthRVO;
import hjho.prj.prct.biz.main.model.MenuAuthVO;
import hjho.prj.prct.biz.main.model.MgrAuthPVO;
import hjho.prj.prct.biz.main.model.MgrAuthRVO;

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

	public MgrAuthRVO getMgrAuth(MgrAuthPVO mgrAuthPVO) {
		String mgrGrpId = mgrAuthPVO.getMgrGrpId();
		String mgrId    = mgrAuthPVO.getMgrId();
		String pageUrl  = mgrAuthPVO.getPageUrl();
		
		MgrAuthRVO mgrAuthRVO = menuAuthMapper.getMgrAuth(mgrAuthPVO);
		
		return mgrAuthRVO;
	}
	
}
