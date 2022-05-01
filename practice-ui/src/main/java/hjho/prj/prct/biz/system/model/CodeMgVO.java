package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class CodeMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private String commCdId;
	
	private String commCdNm;
	
	private String commCdInfo;
	
	private String useYn;
	
	private String addtCdNm;
	
	private String addtCdUseYn;
	
}
