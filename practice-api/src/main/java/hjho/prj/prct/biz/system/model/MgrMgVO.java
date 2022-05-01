package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.biz.table.SysMgr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MgrMgVO", description="시스템 관리자 관리 INPUT")
public class MgrMgVO extends SysMgr {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="testCol", value="테스트컬럼", required=true, allowableValues = "length=20")
	private String testCol;
}
