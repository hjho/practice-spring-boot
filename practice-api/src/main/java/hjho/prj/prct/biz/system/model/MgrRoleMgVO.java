package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.biz.table.SysMgrRole;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MgrRoleMgVO", description="시스템 관리자 그룹 역할 관리 INPUT")
public class MgrRoleMgVO extends SysMgrRole {

	private static final long serialVersionUID = 1L;
	
}
