package hjho.prj.prct.biz.company.model;

import hjho.prj.prct.common.clazz.PagingModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class JobsHsPagingVO extends PagingModel {

	private static final long serialVersionUID = 1L;

	private Long  employeeId;
	
}
