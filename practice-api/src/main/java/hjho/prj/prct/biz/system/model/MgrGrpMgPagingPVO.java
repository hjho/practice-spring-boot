package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.common.clazz.PagingModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@ApiModel(value="MgrGrpMgPagingPVO", description="시스템 관리자 그룹 관리 페이징 INPUT")
public class MgrGrpMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="mgrGrpNm", value="관리자 그룹명")
	private String mgrGrpNm;
	
	@ApiModelProperty(name="useYn", value="사용여부")
	private String useYn;
	
}
