package hjho.prj.prct.biz.mgr.model;

import hjho.prj.prct.biz.table.Employees;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MgrEmpMgPagingPVO", description="관리자 사원 관리 페이징 OUTPUT")
public class MgrEmpMgPagingRVO extends Employees {

	private static final long serialVersionUID = 1L;
	
	// 추가 변수
	@ApiModelProperty(name="employeeName", value="사원 명")
	private String employeeName;
	
	@ApiModelProperty(name="departmentName", value="부서 명")
	private String departmentName;
	
	@ApiModelProperty(name="managerName", value="관리자 명")
	private String managerName;
	
	@ApiModelProperty(name="jobTitle", value="직책 명")
	private String jobTitle;
	
	// 페이지네이션 변수 
	@ApiModelProperty(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@ApiModelProperty(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	
}
