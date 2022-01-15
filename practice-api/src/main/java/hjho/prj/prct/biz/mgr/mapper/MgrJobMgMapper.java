package hjho.prj.prct.biz.mgr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.mgr.model.MgrJobMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrJobMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrJobMgVO;

@Mapper
public interface MgrJobMgMapper {

	List<MgrJobMgPagingRVO> getMgrJob(MgrJobMgPagingPVO mgrJobMgPagingPVO);

	int postMgrJob(MgrJobMgVO mgrJobMgVO);

	int putMgrJob(MgrJobMgVO mgrJobMgVO);

	int deleteMgrJob(MgrJobMgVO mgrJobMgVO);

	int pkCheck(MgrJobMgVO mgrJobMgVO);

}
