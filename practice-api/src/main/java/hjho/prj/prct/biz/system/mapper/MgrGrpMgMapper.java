package hjho.prj.prct.biz.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.system.model.MgrGrpMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrGrpMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrGrpMgVO;

@Mapper
public interface MgrGrpMgMapper {

	int pkCheck(MgrGrpMgVO mgrGrpMgVO);

	List<MgrGrpMgPagingRVO> getSysMgrGrp(MgrGrpMgPagingPVO mgrGrpMgPagingPVO);
	
	int postSysMgrGrp(MgrGrpMgVO mgrGrpMgVO);

	int putSysMgrGrp(MgrGrpMgVO mgrGrpMgVO);

	int deleteSysMgrGrp(MgrGrpMgVO mgrGrpMgVO);

}
