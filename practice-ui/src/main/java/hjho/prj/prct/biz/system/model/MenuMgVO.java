package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class MenuMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private String menuId;
	
	private String hrMenuId;
	
	private String menuNm;
	
	private String menuCtnt;
	
	private String menuCd;
	
	private String pageUrl;
	
	private Long   ordByCls;
	
	private String iconVal;
	
	private String useYn;
	
}
