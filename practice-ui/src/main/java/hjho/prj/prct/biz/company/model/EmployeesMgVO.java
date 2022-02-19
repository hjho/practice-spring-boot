package hjho.prj.prct.biz.company.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class EmployeesMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private Long   employeeId;
		
	private String firstName;
		
	private String lastName; 
		
	private String email; 
		
	private String phoneNumber; 
		
	private String hireDate; 
		
	private String jobId;
		
	private Float  salary;
		
	private Float  commissionPct;
	
	private Long   managerId;
	
	private Long   departmentId;
	
}
