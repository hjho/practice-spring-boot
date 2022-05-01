package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.biz.table.SysCommCdDt;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="CodeDtMgPagingRVO", description="시스템 공통코드 상세 관리 페이징 OUTPUT")
public class CodeDtMgPagingRVO extends SysCommCdDt {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="addtCdNm", value="부속코드명")
	private String addtCdNm;
	
	// 페이지네이션 변수 
	@ApiModelProperty(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@ApiModelProperty(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	
}
