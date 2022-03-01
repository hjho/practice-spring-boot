package hjho.prj.prct.biz.system.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MgrMgMapper {

	int tokenSave(String mgrId, String refreshToken);

}
