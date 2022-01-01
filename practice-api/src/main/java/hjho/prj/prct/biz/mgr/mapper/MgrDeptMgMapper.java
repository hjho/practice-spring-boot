package hjho.prj.prct.biz.mgr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.mgr.model.MgrDeptMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrDeptMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrDeptMgVO;

@Mapper
public interface MgrDeptMgMapper {

	List<MgrDeptMgPagingRVO> getMgrDept(MgrDeptMgPagingPVO mgrDeptMgPagingPVO);

	int postMgrDept(MgrDeptMgVO mgrDeptMgVO);

	int putMgrDept(MgrDeptMgVO mgrDeptMgVO);

	int deleteMgrDept(MgrDeptMgVO mgrDeptMgVO);

	int pkCheck(MgrDeptMgVO mgrDeptMgVO);

}
