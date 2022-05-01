package hjho.prj.prct.biz.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.system.model.MenuMgPagingPVO;
import hjho.prj.prct.biz.system.model.MenuMgPagingRVO;
import hjho.prj.prct.biz.system.model.MenuMgVO;

@Mapper
public interface MenuMgMapper {

	List<MenuMgPagingRVO> getSysMenu(MenuMgPagingPVO menuMgPagingPVO);

	int postSysMenu(MenuMgVO menuMgVO);

	int putSysMenu(MenuMgVO menuMgVO);

	int deleteSysMenu(MenuMgVO menuMgVO);

	int pkCheck(MenuMgVO menuMgVO);

}
