package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.biz.table.SysMgrAuth;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MgrAuthMgVO", description="시스템 관리자 그룹 권한 관리 INPUT")
public class MgrAuthMgVO extends SysMgrAuth {

	private static final long serialVersionUID = 1L;
	
}
