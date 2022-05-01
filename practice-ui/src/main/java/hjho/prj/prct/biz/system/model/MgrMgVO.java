package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class MgrMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private String mgrId;
	
	private Long   empId;
	
	private String mgrPw;
	
	private String mgrNm;
	
	private String sysMgrStatCd;
	
	private Long   pwErrCnt;
	
	private String pwChagDtm;
	
	private String tempPwIssDtm;
	
	private String rcntAccDtm;
	
	private String rfshTk;
	
	private String tkIssDtm;
	
}
