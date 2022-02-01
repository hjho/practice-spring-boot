package hjho.prj.prct.biz.sys.model;

import hjho.prj.prct.biz.table.SysMenuInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="SysMenuMgVO", description="시스템 메뉴 관리 INPUT")
public class SysMenuMgVO extends SysMenuInfo {

	private static final long serialVersionUID = 1L;

}
