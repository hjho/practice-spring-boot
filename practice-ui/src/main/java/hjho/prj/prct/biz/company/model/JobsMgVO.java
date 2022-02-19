package hjho.prj.prct.biz.company.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class JobsMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private String jobId;
	
	private String jobTitle;
	
	private Long   minSalary;
	
	private Long   maxSalary;
	
}
