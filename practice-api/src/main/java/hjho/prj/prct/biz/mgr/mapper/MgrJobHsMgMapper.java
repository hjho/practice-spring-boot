package hjho.prj.prct.biz.mgr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.mgr.model.MgrEmpMgVO;
import hjho.prj.prct.biz.mgr.model.MgrJobHsMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrJobHsMgPagingRVO;

@Mapper
public interface MgrJobHsMgMapper {

	List<MgrJobHsMgPagingRVO> getMgrJobHs(MgrJobHsMgPagingPVO mgrJobHsMgPagingPVO);

	int deleteMgrJobHs(MgrEmpMgVO mgrEmpMgVO);

	int pkCheck(MgrEmpMgVO mgrEmpMgVO);

}
