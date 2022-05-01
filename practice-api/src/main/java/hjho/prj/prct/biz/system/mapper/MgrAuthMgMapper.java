package hjho.prj.prct.biz.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.system.model.MgrAuthMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrAuthMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrAuthMgVO;

@Mapper
public interface MgrAuthMgMapper {

	int pkCheck(MgrAuthMgVO mgrAuthMgVO);

	List<MgrAuthMgPagingRVO> getSysMgrAuth(MgrAuthMgPagingPVO mgrAuthMgPagingPVO);
	
	List<MgrAuthMgPagingRVO> getSysMgrAuthMenu(MgrAuthMgPagingPVO mgrAuthMgPagingPVO);
	
	int postSysMgrAuth(MgrAuthMgVO mgrAuthMgVO);

	int putSysMgrAuth(MgrAuthMgVO mgrAuthMgVO);

	int deleteSysMgrAuth(MgrAuthMgVO mgrAuthMgVO);

}
