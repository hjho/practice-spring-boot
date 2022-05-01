package hjho.prj.prct.biz.company.model;

import hjho.prj.prct.common.clazz.PagingModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class EmployeesMgPagingVO extends PagingModel {

	private static final long serialVersionUID = 1L;
	
	private String employeeId;
	
	private String firstName;
	
	private String lastName; 
	
	private String hireDate; 
	
	private String jobId;
	
	private Long   managerId;
    
	private Long   departmentId;
    
}
