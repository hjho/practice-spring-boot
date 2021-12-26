package hjho.prj.prct.common.clazz;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class PagingModel extends CommonModel {
	
	private static final long serialVersionUID = 1L;

	@ApiParam(name="rowNo", value="행 번호", hidden=true)
	private long   rowNo;
	
	@ApiParam(name="page", value="페이지 번호")
	private long   page;
	
	@ApiParam(name="length", value="한 페이지 당 행 갯수")
	private long   length;
	
	@ApiParam(name="totalCnt", value="전체 행 갯수", hidden=true)
	private long   totalCnt;
	
	@ApiParam(name="sortType", value="오름차순 내림차순")
	private String sortType;
	
	@ApiParam(name="sortCol", value="테이블 컬럼 인덱스")
	private long   sortCol;
	
}
