package hjho.prj.prct.biz.main.mapper;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.main.model.LoginPVO;
import hjho.prj.prct.biz.main.model.MgrInfoVO;

@Mapper
public interface LoginMapper {

	public MgrInfoVO loginProc(LoginPVO loginPVO);

	public int idCheck(LoginPVO loginPVO);

}