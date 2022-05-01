package hjho.prj.prct.biz.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.system.model.CodeDtMgPagingPVO;
import hjho.prj.prct.biz.system.model.CodeDtMgPagingRVO;
import hjho.prj.prct.biz.system.model.CodeDtMgVO;

@Mapper
public interface CodeDtMgMapper {

	List<CodeDtMgPagingRVO> getSysCodeDt(CodeDtMgPagingPVO codeDtMgPagingPVO);

	int postSysCodeDt(CodeDtMgVO codeDtMgVO);

	int putSysCodeDt(CodeDtMgVO codeDtMgVO);
	
	int putSysCodeDtSort(CodeDtMgVO codeDtMgVO);

	int deleteSysCodeDt(CodeDtMgVO codeDtMgVO);

	int pkCheck(CodeDtMgVO codeDtMgVO);

}
