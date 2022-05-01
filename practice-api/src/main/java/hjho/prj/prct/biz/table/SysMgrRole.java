package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysMgrRole extends CommonModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="mgrGrpId", value="관리자 그룹 아이디")
	private String mgrGrpId;
	
	@ApiModelProperty(name="mgrId", value="관리자 아이디")
	private String mgrId;
	
	@ApiModelProperty(name="apitDtm", value="발령일시")
	private String apitDtm;
	
	@ApiModelProperty(name="exprDtm", value="만료일시")
	private String exprDtm;
	
	@ApiModelProperty(name="useYn", value="사용여부")
	private String useYn;
	
}
