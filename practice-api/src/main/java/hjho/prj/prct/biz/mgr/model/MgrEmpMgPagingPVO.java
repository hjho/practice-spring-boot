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
	
	@ApiModelProperty(name="employeeId", value="사원 번호")
	private String employeeId;
	
	@ApiModelProperty(name="firstName", value="이름")
	private String firstName;
	
	@ApiModelProperty(name="lastName", value="성")
	private String lastName; 
	
	@ApiModelProperty(name="hireDate", value="고용일자")
	private String hireDate; 
	
	@ApiModelProperty(name="jobId", value="직책")
	private String jobId;
	
    @ApiModelProperty(name="managerId", value="부서장 아이디")
	private Long   managerId;
    
    @ApiModelProperty(name="departmentId", value="부서 번호")
	private Long   departmentId;
	
}
