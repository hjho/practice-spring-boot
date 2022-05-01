package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.biz.table.SysMgrAuth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MgrAuthMgPagingRVO", description="시스템 관리자 그룹 권한 관리 페이징 OUTPUT")
public class MgrAuthMgPagingRVO extends SysMgrAuth {

	private static final long serialVersionUID = 1L;
	
	// 추가 변수
	@ApiModelProperty(name="menuNm", value="메뉴명")
	private String menuNm;
	
	@ApiModelProperty(name="menuCd", value="메뉴코드")
	private String menuCd;
	
	// 페이지네이션 변수 
	@ApiModelProperty(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@ApiModelProperty(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	
}
