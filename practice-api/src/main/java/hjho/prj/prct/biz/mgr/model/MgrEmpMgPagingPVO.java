package hjho.prj.prct.biz.mgr.model;

import hjho.prj.prct.common.clazz.PagingModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MgrEmpMgPagingPVO", description="관리자 사원 관리 페이징 INPUT")
public class MgrEmpMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="employeeIid", value="사원 번호")
	private String employeeIid;
	
	@ApiModelProperty(name="firstName", value="이름")
	private String firstName;
	
	@ApiModelProperty(name="lastName", value="성")
	private String lastName; 
	
//	@ApiModelProperty(name="email", value="이메일")
//	private String email; 
//	
//	@ApiModelProperty(name="phoneNumber", value="전화번호")
//	private String phoneNumber; 
	
	@ApiModelProperty(name="hireDate", value="고용일자")
	private String hireDate; 
	
	@ApiModelProperty(name="jobId", value="직책")
	private String jobId;
	
//	@ApiModelProperty(name="salary", value="급여")
//	private String salary;
//	
//	@ApiModelProperty(name="commissionPct", value="수수료(영업사원)")
//	private String commissionPct;
    
    @ApiModelProperty(name="managerId", value="부서장 아이디")
	private String managerId;
    
    @ApiModelProperty(name="departmentId", value="부서 번호")
	private String departmentId;
	
}
