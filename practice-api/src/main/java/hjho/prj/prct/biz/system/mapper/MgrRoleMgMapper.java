package hjho.prj.prct.biz.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.system.model.MgrMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrRoleMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrRoleMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrRoleMgVO;

@Mapper
public interface MgrRoleMgMapper {

	int pkCheck(MgrRoleMgVO mgrRoleMgVO);

	List<MgrRoleMgPagingRVO> getSysMgrRole(MgrRoleMgPagingPVO mgrRoleMgPagingPVO);
	
	List<MgrMgPagingRVO> getSysMgrRoleMgr(MgrRoleMgPagingPVO mgrRoleMgPagingPVO);
	
	int postSysMgrRole(MgrRoleMgVO mgrRoleMgVO);

	int putSysMgrRole(MgrRoleMgVO mgrRoleMgVO);

	int deleteSysMgrRole(MgrRoleMgVO mgrRoleMgVO);

}
