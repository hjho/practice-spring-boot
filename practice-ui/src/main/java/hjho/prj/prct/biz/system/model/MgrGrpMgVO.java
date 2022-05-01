package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class MgrGrpMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private String mgrGrpId;
	
	private String mgrGrpNm;
	
	private String mgrGrpInfo;
	
	private String useYn;
	
}
