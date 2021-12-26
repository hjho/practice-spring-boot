package hjho.prj.prct.biz.main.model;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class LoginRVO extends CommonModel {

	private static final long serialVersionUID = 1L;
	
	@ApiParam(name="userId", value="아이디")
	private String userId;
	
	@ApiParam(name="userPw", value="패스워드")
	private String userPw;
	
	@ApiParam(name="userNm", value="이름")
	private String userNm;
	
}
