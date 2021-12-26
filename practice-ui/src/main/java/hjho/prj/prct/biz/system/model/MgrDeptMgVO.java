package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class MgrDeptMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private long   departmentId;
	
	private String departmentName;
	
	private long   managerId;
			
	private long   locationId;
	
}
