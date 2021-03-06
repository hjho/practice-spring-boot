package hjho.prj.prct.biz.main.model;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="LoginPVO", description="로그인 검증 INPUT")
public class LoginPVO extends CommonModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="userId", value="아이디")
	private String userId;
	
	@ApiModelProperty(name="userPw", value="패스워드")
	private String userPw;
	
	@ApiModelProperty(name="mgrGrpId", value="관리자 그룹 아이디")
	private String mgrGrpId;
	
}
