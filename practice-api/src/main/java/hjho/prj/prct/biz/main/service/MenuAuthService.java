package hjho.prj.prct.biz.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.mapper.MenuAuthMapper;
import hjho.prj.prct.biz.main.model.MenuAuthPVO;
import hjho.prj.prct.biz.main.model.MenuAuthRVO;
import hjho.prj.prct.biz.main.model.MenuAuthVO;

@Service
public class MenuAuthService { 
	
	@Autowired
	private MenuAuthMapper menuAuthMapper;
	
	public List<MenuAuthRVO> getMainMenuAuth(MenuAuthPVO menuAuthPVO) {
		
		List<MenuAuthRVO> menuHrList = menuAuthMapper.getMenuAuthHr(menuAuthPVO);
		
		for (MenuAuthRVO mainMenuAuthVO : menuHrList) {
			List<MenuAuthVO> menuLr = menuAuthMapper.getMenuAuthLr(mainMenuAuthVO);
			mainMenuAuthVO.setMenuLr(menuLr);
		}
		
		return menuHrList;
	}
	
}
