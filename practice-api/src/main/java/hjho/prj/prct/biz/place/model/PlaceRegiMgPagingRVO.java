package hjho.prj.prct.biz.place.model;

import hjho.prj.prct.biz.table.Regions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="CuntMgPagingRVO", description="국가 관리 페이징 OUTPUT")
public class PlaceRegiMgPagingRVO extends Regions {

	private static final long serialVersionUID = 1L;
	
	// 페이지네이션 변수 
	@ApiModelProperty(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@ApiModelProperty(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	
}
