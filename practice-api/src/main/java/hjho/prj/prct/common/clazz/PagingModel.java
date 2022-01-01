package hjho.prj.prct.common.clazz;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class PagingModel extends CommonModel {
	
	private static final long serialVersionUID = 1L;
	
	/* INPUT PARAMETER
	@ApiParam(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@ApiParam(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	*/
	@ApiParam(name="page", value="페이지 번호")
	private Long   page;
	
	@ApiParam(name="length", value="한 페이지 당 행 갯수")
	private Long   length;

	@ApiParam(name="sortType", value="오름차순 내림차순")
	private String sortType;
	
	@ApiParam(name="sortCol", value="테이블 컬럼 인덱스")
	private Long   sortCol;
	
}
