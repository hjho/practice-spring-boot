package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Employees extends CommonModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiParam(name="employeeId", value="사원 번호")
	private String employeeId;
		
	@ApiParam(name="firstName", value="이름")
	private String firstName;
		
	@ApiParam(name="lastName", value="성")
	private String lastName; 
		
	@ApiParam(name="email", value="이메일")
	private String email; 
		
	@ApiParam(name="phoneNumber", value="전화번호")
	private String phoneNumber; 
		
	@ApiParam(name="hireDate", value="고용일자")
	private String hireDate; 
		
	@ApiParam(name="jobId", value="직책")
	private String jobId;
		
	@ApiParam(name="salary", value="급여")
	private String salary;
		
	@ApiParam(name="commissionPct", value="수수료(영업사원)")
	private String commissionPct;
	
	@ApiParam(name="managerId", value="부서장 아이디")
	private String managerId;
	
	@ApiParam(name="departmentId", value="부서 번호")
	private String departmentId;

}
