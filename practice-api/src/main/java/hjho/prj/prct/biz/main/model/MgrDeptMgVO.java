package hjho.prj.prct.biz.main.model;

import hjho.prj.prct.biz.table.Departments;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MgrDeptMgVO", description="관리자 부서 관리 INPUT")
public class MgrDeptMgVO extends Departments {

	private static final long serialVersionUID = 1L;

}
