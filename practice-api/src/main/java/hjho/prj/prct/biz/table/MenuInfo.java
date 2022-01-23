package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class MenuInfo extends CommonModel {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name="menuId", value="메뉴 아이디")
	private String menuId;
	
	@ApiModelProperty(name="hrMenuId", value="상위 메뉴 아이디")
	private String hrMenuId;
	
	@ApiModelProperty(name="menuNm", value="메뉴 명")
	private String menuNm;
	
	@ApiModelProperty(name="menuCtnt", value="메뉴 내용")
	private String menuCtnt;
	
	@ApiModelProperty(name="pageUrl", value="페이지 URL")
	private String pageUrl;
	
	@ApiModelProperty(name="ordByCls", value="계층별 순서")
	private Long   ordByCls;
	
	@ApiModelProperty(name="iconUrl", value="아이콘값")
	private String iconUrl;
	
	@ApiModelProperty(name="useYn", value="사용여부")
	private String useYn;
	
}
