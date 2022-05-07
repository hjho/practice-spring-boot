package hjho.prj.prct.biz.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.system.model.MgrMgPagingPVO;
import hjho.prj.prct.biz.system.model.MgrMgPagingRVO;
import hjho.prj.prct.biz.system.model.MgrMgVO;

@Mapper
public interface MgrMgMapper {

	int tokenSave(String mgrId, String refreshToken);

	String getToken(String mgrId);
	
	int pkCheck(MgrMgVO mgrMgVO);

	List<MgrMgPagingRVO> getSysMgr(MgrMgPagingPVO mgrMgPagingPVO);
	
	String getSysMgrId();
	
	int postSysMgr(MgrMgVO mgrMgVO);

	int putSysMgr(MgrMgVO mgrMgVO);

	int deleteSysMgr(MgrMgVO mgrMgVO);

}
