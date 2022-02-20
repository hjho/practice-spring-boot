package hjho.prj.prct.biz.main.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="MgrInfoVO", description="관리자 정보 OUTPUT")
public class MgrInfoVO {

	@ApiModelProperty(name="mgrGrpId", value="관리자 그룹 아이디")
	private String mgrGrpId;
	
	@ApiModelProperty(name="mgrGrpNm", value="관리자 그룹 명")
	private String mgrGrpNm;
	
	@ApiModelProperty(name="mgrId", value="관리자 아이디")
	private String mgrId;
	
	@ApiModelProperty(name="mgrNm", value="관리자 명")
	private String mgrNm;
	
	@ApiModelProperty(name="sysMgrStatCd", value="시스템 관리자 상태 코드")
	private String sysMgrStatCd;
	
	@ApiModelProperty(name="apitDtm", value="발령 일시")
	private String apitDtm;
	
	@ApiModelProperty(name="exprDtm", value="만료 일시")
	private String exprDtm;
	
}
