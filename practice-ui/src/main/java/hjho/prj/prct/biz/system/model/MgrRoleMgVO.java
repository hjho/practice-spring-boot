package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class MgrRoleMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private String mgrGrpId;
	
	private String mgrId;
	
	private String apitDtm;
	
	private String exprDtm;
	
	private String useYn;
	
}
