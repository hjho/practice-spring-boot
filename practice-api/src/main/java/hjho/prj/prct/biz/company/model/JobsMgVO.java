package hjho.prj.prct.biz.company.model;

import hjho.prj.prct.biz.table.Jobs;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MgrDeptMgVO", description="관리자 부서 관리 INPUT")
public class JobsMgVO extends Jobs {

	private static final long serialVersionUID = 1L;

}
