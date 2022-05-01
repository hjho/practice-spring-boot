package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class CodeDtMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private String commCdId;
	
	private String cdVal;
	
	private String cdValNm;
	
	private String addtCdVal;
	
	private String sortOrd;
	
	private String useYn;
	
}
