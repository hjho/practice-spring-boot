package hjho.prj.prct.biz.main.mapper;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.main.model.LoginPVO;
import hjho.prj.prct.biz.main.model.LoginRVO;

@Mapper
public interface LoginMapper {

	public LoginRVO loginProc(LoginPVO loginPVO);

}
