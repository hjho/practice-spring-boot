package hjho.prj.prct.biz.mgr.model;

import hjho.prj.prct.biz.table.Locations;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="LocMgPagingPVO", description="위치 관리 페이징 OUTPUT")
public class MgrLocMgPagingRVO extends Locations {

	private static final long serialVersionUID = 1L;
	
	// 추가 변수
	@ApiModelProperty(name="countryName", value="국가 명")
	private String countryName;
	
	// 페이지네이션 변수 
	@ApiModelProperty(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@ApiModelProperty(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	
}
