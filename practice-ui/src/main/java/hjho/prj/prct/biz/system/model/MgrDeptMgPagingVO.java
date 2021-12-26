package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.common.clazz.PagingModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class MgrDeptMgPagingVO extends PagingModel {

	private static final long serialVersionUID = 1L;

	private String departmentName;
	
	private long   locationId;
	
}
