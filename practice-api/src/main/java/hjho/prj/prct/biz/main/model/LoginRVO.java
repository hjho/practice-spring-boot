package hjho.prj.prct.biz.main.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="LoginRVO", description="로그인 검증 OUTPUT")
public class LoginRVO {

	@ApiModelProperty(name="userId", value="아이디")
	private String userId;
	
	@ApiModelProperty(name="userNm", value="이름")
	private String userNm;
	
}
