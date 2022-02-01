package hjho.prj.prct.biz.sys.model;

import hjho.prj.prct.biz.table.SysMenuInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="SysMenuMgPagingRVO", description="시스템 메뉴 관리 페이징 OUTPUT")
public class SysMenuMgPagingRVO extends SysMenuInfo {

	private static final long serialVersionUID = 1L;
	
	// 추가 변수
	@ApiModelProperty(name="lvl", value="계층")
	private Long   lvl;
	
	@ApiModelProperty(name="hrMenuNm", value="상위 메뉴 명")
	private String hrMenuNm;
	
	@ApiModelProperty(name="lrMenuCnt", value="하위 메뉴 수")
	private Long   lrMenuCnt;
	
	// 페이지네이션 변수 
	@ApiModelProperty(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@ApiModelProperty(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	
}
