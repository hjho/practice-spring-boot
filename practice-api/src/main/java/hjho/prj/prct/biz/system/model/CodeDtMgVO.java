package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.biz.table.SysCommCdDt;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="CodeDtMgVO", description="시스템 공통코드 상세 관리 INPUT")
public class CodeDtMgVO extends SysCommCdDt {

	private static final long serialVersionUID = 1L;

}
