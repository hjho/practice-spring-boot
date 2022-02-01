package hjho.prj.prct.biz.sys.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class SysMenuMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private String menuId;
	
	private String hrMenuId;
	
	private String menuNm;
	
	private String menuCtnt;
	
	private String pageUrl;
	
	private Long   ordByCls;
	
	private String iconVal;
	
	private String useYn;
	
}
