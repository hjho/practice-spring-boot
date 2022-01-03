package hjho.prj.prct.biz.mgr.model;

import hjho.prj.prct.biz.table.Employees;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MgrEmpMgVO", description="관리자 사원 관리 INPUT")
public class MgrEmpMgVO extends Employees {

	private static final long serialVersionUID = 1L;

}
