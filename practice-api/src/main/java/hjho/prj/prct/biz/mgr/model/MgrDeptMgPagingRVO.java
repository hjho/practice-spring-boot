package hjho.prj.prct.biz.mgr.model;

import hjho.prj.prct.biz.table.Departments;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class MgrDeptMgPagingRVO extends Departments {

	private static final long serialVersionUID = 1L;
	
	// 추가 변수
	@ApiParam(name="managerName", value="부서장 명")
	private String managerName;
	
	@ApiParam(name="locationName", value="지역 명")
	private String locationName;
		
	// 페이지네이션 변수 
	@ApiParam(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@ApiParam(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	
}
