package hjho.prj.prct.biz.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.system.model.MgrDeptMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrDeptMgPagingRVO;

@Mapper
public interface MgrDeptMgMapper {

	List<MgrDeptMgPagingRVO> getMgrDept(MgrDeptMgPagingPVO mgrDeptMgPagingPVO);

}
