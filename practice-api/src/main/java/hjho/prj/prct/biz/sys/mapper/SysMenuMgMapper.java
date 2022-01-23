package hjho.prj.prct.biz.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.sys.model.SysMenuMgPagingPVO;
import hjho.prj.prct.biz.sys.model.SysMenuMgPagingRVO;
import hjho.prj.prct.biz.sys.model.SysMenuMgVO;

@Mapper
public interface SysMenuMgMapper {

	List<SysMenuMgPagingRVO> getSysMenu(SysMenuMgPagingPVO sysMenuMgPagingPVO);

	int postSysMenu(SysMenuMgVO sysMenuMgVO);

	int putSysMenu(SysMenuMgVO sysMenuMgVO);

	int deleteSysMenu(SysMenuMgVO sysMenuMgVO);

	int pkCheck(SysMenuMgVO sysMenuMgVO);

}
