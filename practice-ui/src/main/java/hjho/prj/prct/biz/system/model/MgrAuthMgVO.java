package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class MgrAuthMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;
	
	private String mgrGrpId;
	
	private String menuId;
	
	private String readAuthYn;
	
	private String cretAuthYn;
	
	private String updAuthYn;
	
	private String delAuthYn;
	
	private String exptAuthYn;
	
	private String privDataReadAuthYn;
	
	private String useYn;
}
