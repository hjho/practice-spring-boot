package hjho.prj.prct.biz.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.system.model.MgrEmpMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrEmpMgPagingRVO;

@Mapper
public interface MgrEmpMgMapper {

	List<MgrEmpMgPagingRVO> getMgrEmp(MgrEmpMgPagingPVO mgrDeEmpMgPagingPVO);

}
