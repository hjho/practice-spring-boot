package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.biz.table.SysMgrRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MgrRoleMgPagingRVO", description="시스템 관리자 그룹 역할 관리 페이징 OUTPUT")
public class MgrRoleMgPagingRVO extends SysMgrRole {

	private static final long serialVersionUID = 1L;
	
	// 추가 변수
	@ApiModelProperty(name="mgrNm", value="관리자 명")
	private String mgrNm;
	
	// 페이지네이션 변수 
	@ApiModelProperty(name="rowNo", value="행 번호")
	private Long   rowNo;
	
	@ApiModelProperty(name="totalCnt", value="전체 행 갯수")
	private Long   totalCnt;
	
}
