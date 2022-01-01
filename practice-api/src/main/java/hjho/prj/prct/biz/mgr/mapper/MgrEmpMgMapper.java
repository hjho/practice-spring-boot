package hjho.prj.prct.biz.mgr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.mgr.model.MgrEmpMgPagingPVO;
import hjho.prj.prct.biz.mgr.model.MgrEmpMgPagingRVO;

@Mapper
public interface MgrEmpMgMapper {

	List<MgrEmpMgPagingRVO> getMgrEmp(MgrEmpMgPagingPVO mgrDeEmpMgPagingPVO);

}
