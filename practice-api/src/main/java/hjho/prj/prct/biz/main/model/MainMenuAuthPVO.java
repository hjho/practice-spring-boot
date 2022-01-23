package hjho.prj.prct.biz.main.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MainMenuAuthPVO", description="메인 메뉴 권한 INPUT")
public class MainMenuAuthPVO {
	
	@ApiModelProperty(name="mgrId", value="관리자아이디")
	private String mgrId;
	
}
