package hjho.prj.prct.biz.mgr.model;

import hjho.prj.prct.biz.table.JobHistory;
import hjho.prj.prct.biz.table.Jobs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MgrJobMgPagingRVO", description="관리자 직책 관리 페이징 OUTPUT")
public class MgrJobHsMgPagingRVO extends JobHistory {

	private static final long serialVersionUID = 1L;
	// 추가 
	@ApiModelProperty(name="jobTitle", value="직책명")
	private String jobTitle;
	
	@ApiModelProperty(name="employeeName", value="사원명")
	private String employeeName;
	
	@ApiModelProperty(name="departmentName", value="부서이름")
	private String departmentName;
		
	// 페이지네이션 변수 
	@ApiModelProperty(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@ApiModelProperty(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	
}
