package hjho.prj.prct.biz.mgr.model;

import hjho.prj.prct.biz.table.Departments;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MgrDeptMgPagingRVO", description="관리자 부서 관리 페이징 OUTPUT")
public class MgrDeptMgPagingRVO extends Departments {

	private static final long serialVersionUID = 1L;
	
	// 추가 변수
	@ApiModelProperty(name="managerName", value="부서장 명")
	private String managerName;
	
	@ApiModelProperty(name="locationName", value="지역 명")
	private String locationName;
		
	// 페이지네이션 변수 
	@ApiModelProperty(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@ApiModelProperty(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	
}
