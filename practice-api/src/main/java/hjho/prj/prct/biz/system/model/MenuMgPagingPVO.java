package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.common.clazz.PagingModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@ApiModel(value="SysMenuMgPagingPVO", description="시스템 메뉴 관리 페이징 INPUT")
public class MenuMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="menuNm", value="메뉴 명")
	private String menuNm;
	
	@ApiModelProperty(name="useYn", value="사용여부")
	private String useYn;
	
	@ApiModelProperty(name="hrMenuYn", value="상위메뉴여부")
	private String hrMenuYn;
	
}
