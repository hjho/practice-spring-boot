package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysMgrGrp extends CommonModel {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name="mgrGrpId", value="관리자 그룹 아이디")
	private String mgrGrpId;
	
	@ApiModelProperty(name="mgrGrpNm", value="관리자 그룹 명")
	private String mgrGrpNm;
	
	@ApiModelProperty(name="mgrGrpInfo", value="관리자 그룹 정보")
	private String mgrGrpInfo;
	
	@ApiModelProperty(name="useYn", value="사용여부")
	private String useYn;
	
}
