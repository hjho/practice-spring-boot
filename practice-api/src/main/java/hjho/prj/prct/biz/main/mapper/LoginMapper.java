package hjho.prj.prct.biz.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.main.model.LoginPVO;
import hjho.prj.prct.biz.main.model.MgrInfoVO;

@Mapper
public interface LoginMapper {

	public List<MgrInfoVO> loginProc(LoginPVO loginPVO);

	public int idCheck(LoginPVO loginPVO);

	public int pwCheck(LoginPVO loginPVO);

	public int errPw(LoginPVO loginPVO);

}