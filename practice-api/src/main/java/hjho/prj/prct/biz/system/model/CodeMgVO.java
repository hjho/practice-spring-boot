package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.biz.table.SysCommCdBs;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="CodeMgVO", description="시스템 공통코드 관리 INPUT")
public class CodeMgVO extends SysCommCdBs {

	private static final long serialVersionUID = 1L;

}
