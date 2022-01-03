package hjho.prj.prct.common.clazz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(value="PagingModel", description="페이지네이션 입력")
public class PagingModel extends CommonModel {
	
	private static final long serialVersionUID = 1L;
	
	/* INPUT PARAMETER
	@@ApiModelProperty(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@@ApiModelProperty(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	*/
	@ApiModelProperty(name="page", value="페이지 번호", example="1")
	private Long   page;
	
	@ApiModelProperty(name="length", value="한 페이지 당 행 갯수", example="10")
	private Long   length;

	@ApiModelProperty(name="sortType", value="오름차순 내림차순", example="asc")
	private String sortType;
	
	@ApiModelProperty(name="sortCol", value="테이블 컬럼 인덱스", example="0")
	private Long   sortCol;
	
}
