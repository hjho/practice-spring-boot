package hjho.prj.prct.biz.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.main.mapper.MainMenuAuthMapper;
import hjho.prj.prct.biz.main.model.MainMenuAuthPVO;
import hjho.prj.prct.biz.main.model.MainMenuAuthRVO;
import hjho.prj.prct.biz.main.model.MenuAuthVO;

@Service
public class MainMenuAuthService { 
	
	@Autowired
	private MainMenuAuthMapper mainMenuAuthMapper;
	
	public List<MainMenuAuthRVO> getMainMenuAuth(MainMenuAuthPVO mainMenuAuthPVO) {
		
		List<MainMenuAuthRVO> menuHrList = mainMenuAuthMapper.getMenuAuthHr(mainMenuAuthPVO);
		
		for (MainMenuAuthRVO mainMenuAuthVO : menuHrList) {
			List<MenuAuthVO> menuLr = mainMenuAuthMapper.getMenuAuthLr(mainMenuAuthVO);
			mainMenuAuthVO.setMenuLr(menuLr);
		}
		
		return menuHrList;
	}
	
}
