package hjho.prj.prct.biz.mgr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.mgr.model.MgrLocMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrLocMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrLocMgVO;

@Mapper
public interface MgrLocMgMapper {

	List<MgrLocMgPagingRVO> getMgrLoc(MgrLocMgPagingPVO mgrLocMgPagingPVO);

	int postMgrLoc(MgrLocMgVO mgrLocMgVO);

	int putMgrLoc(MgrLocMgVO mgrLocMgVO);

	int deleteMgrLoc(MgrLocMgVO mgrLocMgVO);

	int pkCheck(MgrLocMgVO mgrLocMgVO);

}
