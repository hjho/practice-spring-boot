package hjho.prj.prct.biz.place.model;

import hjho.prj.prct.biz.table.Countries;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class CuntMgPagingRVO extends Countries {

	private static final long serialVersionUID = 1L;
	
	// 추가 변수 
	@ApiParam(name="regionName", value="대륙 이름")
	private String regionName;
	
	// 페이지네이션 변수 
	@ApiParam(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@ApiParam(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	
}
