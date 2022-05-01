package hjho.prj.prct.biz.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.main.model.MenuAuthPVO;
import hjho.prj.prct.biz.main.model.MenuAuthRVO;
import hjho.prj.prct.biz.main.model.MenuAuthVO;
import hjho.prj.prct.biz.main.model.MgrAuthPVO;
import hjho.prj.prct.biz.main.model.MgrAuthRVO;

@Mapper
public interface MenuAuthMapper {

	public List<MenuAuthRVO> getMenuAuthHr(MenuAuthPVO menuAuthPVO);

	public List<MenuAuthVO> getMenuAuthLr(MenuAuthPVO menuAuthPVO);

	public MgrAuthRVO getMgrAuth(MgrAuthPVO mgrAuthPVO);

}
