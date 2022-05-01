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
@ApiModel(value="MgrRoleMgPagingPVO", description="시스템 관리자 그룹 역할 관리 페이징 INPUT")
public class MgrRoleMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="mgrGrpId", value="관리자 그룹 아이디")
	private String mgrGrpId;
	
	@ApiModelProperty(name="roleMgrYn", value="소속 관리자 여부")
	private String roleMgrYn;
	
}
