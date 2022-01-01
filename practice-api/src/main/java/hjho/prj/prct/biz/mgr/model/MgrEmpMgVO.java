package hjho.prj.prct.biz.mgr.model;

import hjho.prj.prct.biz.table.Employees;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class MgrEmpMgVO extends Employees {

	private static final long serialVersionUID = 1L;

}
