package hjho.prj.prct.biz.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.main.model.MenuAuthPVO;
import hjho.prj.prct.biz.main.model.MenuAuthRVO;
import hjho.prj.prct.biz.main.model.MenuAuthVO;

@Mapper
public interface MenuAuthMapper {

	public List<MenuAuthRVO> getMenuAuthHr(MenuAuthPVO menuAuthPVO);

	public List<MenuAuthVO> getMenuAuthLr(MenuAuthRVO menuAuthRVO);

}
