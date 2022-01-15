package hjho.prj.prct.biz.mgr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.mgr.model.MgrEmpMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgPagingRVO;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgVO;

@Mapper
public interface MgrEmpMgMapper {

	List<MgrEmpMgPagingRVO> getMgrEmp(MgrEmpMgPagingPVO mgrDeEmpMgPagingPVO);

	int postMgrEmp(MgrEmpMgVO mgrEmpMgVO);

	int putMgrEmp(MgrEmpMgVO mgrEmpMgVO);

	int deleteMgrEmp(MgrEmpMgVO mgrEmpMgVO);

	int pkCheck(MgrEmpMgVO mgrEmpMgVO);
	
	int emailCheck(MgrEmpMgVO mgrEmpMgVO);

	MgrEmpMgVO inrqMgrEmp(Long employeeId);

	int empDelJobHsCheck(MgrEmpMgVO mgrEmpMgVO);

}
