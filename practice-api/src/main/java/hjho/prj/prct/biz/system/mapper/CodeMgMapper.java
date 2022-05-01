package hjho.prj.prct.biz.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.system.model.CodeMgPagingPVO;
import hjho.prj.prct.biz.system.model.CodeMgPagingRVO;
import hjho.prj.prct.biz.system.model.CodeMgVO;

@Mapper
public interface CodeMgMapper {

	List<CodeMgPagingRVO> getSysCode(CodeMgPagingPVO codeMgPagingPVO);

	int postSysCode(CodeMgVO codeMgVO);

	int putSysCode(CodeMgVO codeMgVO);

	int deleteSysCode(CodeMgVO codeMgVO);

	int pkCheck(CodeMgVO codeMgVO);

}
