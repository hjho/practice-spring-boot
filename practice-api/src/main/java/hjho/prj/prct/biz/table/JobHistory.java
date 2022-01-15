package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="JobHistory", description="직책 히스토리 테이블 모델")
public class JobHistory extends CommonModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="employeeId", value="사원번호")
	private String employeeId;
	
	@ApiModelProperty(name="startDate", value="시작일시")
	private String startDate;
	
	@ApiModelProperty(name="endDate", value="마지막일시")
	private String endDate;
	
	@ApiModelProperty(name="jobId", value="직책 아이디")
	private String jobId;
	
	@ApiModelProperty(name="departmentId", value="부서번호")
	private String departmentId;
	
}
