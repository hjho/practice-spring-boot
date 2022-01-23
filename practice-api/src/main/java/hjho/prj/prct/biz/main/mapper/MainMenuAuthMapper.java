package hjho.prj.prct.biz.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.main.model.MainMenuAuthPVO;
import hjho.prj.prct.biz.main.model.MainMenuAuthRVO;
import hjho.prj.prct.biz.main.model.MenuAuthVO;

@Mapper
public interface MainMenuAuthMapper {

	public List<MainMenuAuthRVO> getMenuAuthHr(MainMenuAuthPVO mainMenuAuthPVO);

	public List<MenuAuthVO> getMenuAuthLr(MainMenuAuthRVO mainMenuAuthRVO);

}
