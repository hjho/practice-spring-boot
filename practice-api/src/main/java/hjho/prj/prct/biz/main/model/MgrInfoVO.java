package hjho.prj.prct.biz.main.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="MgrInfoVO", description="관리자 정보 OUTPUT")
public class MgrInfoVO {

	@ApiModelProperty(name="mgrId", value="관리자 아이디")
	private String mgrId;
	
	@ApiModelProperty(name="mgrNm", value="관리자 명")
	private String mgrNm;
	
	@ApiModelProperty(name="mgrGrpId", value="관리자 그룹 아이디")
	private String mgrGrpId;
	
}
