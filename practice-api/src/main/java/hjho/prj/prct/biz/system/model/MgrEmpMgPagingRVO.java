package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.biz.table.Employees;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class MgrEmpMgPagingRVO extends Employees {

	private static final long serialVersionUID = 1L;
	
	// 추가 변수
	@ApiParam(name="departmentName", value="부서 명")
	private String departmentName;
	
	// 페이지네이션 변수 
	@ApiParam(name="rowNo", value="행 번호")
	private long   rowNo;
	
	@ApiParam(name="page", value="페이지 번호")
	private long   page;
	
	@ApiParam(name="length", value="한 페이지 당 행 갯수")
	private long   length;
	
	@ApiParam(name="totalCnt", value="전체 행 갯수")
	private long   totalCnt;
	
	@ApiParam(name="sortType", value="오름차순 내림차순")
	private String sortType;
	
	@ApiParam(name="sortCol", value="테이블 컬럼 인덱스")
	private long   sortCol;
	
}
