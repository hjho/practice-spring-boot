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
@ApiModel(value="MgrAuthMgPagingPVO", description="시스템 관리자 그룹 권한 관리 페이징 INPUT")
public class MgrAuthMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="mgrGrpId", value="관리자 그룹 아이디")
	private String mgrGrpId;
	
}
