package hjho.prj.prct.biz.main.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MgrAuthPVO {

	@ApiModelProperty(name="method", value="유형")
	private String method;
	
	@ApiModelProperty(name="pageUrl", value="페이지 URL")
	private String pageUrl;
	
	@ApiModelProperty(name="mgrGrpId", value="관리자 그룹 아이디")
	private String mgrGrpId;
	
	@ApiModelProperty(name="mgrId", value="관리자 아이디")
	private String mgrId;
	
}
