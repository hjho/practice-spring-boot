package hjho.prj.prct.biz.main.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class MainMenuMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private String menuId;
	
	private String hrMenuId;
	
	private String pageUrl;
	
	private Long   ordByCls;
	
	private String iconUrl;
	
	private String useYn;
	
}
