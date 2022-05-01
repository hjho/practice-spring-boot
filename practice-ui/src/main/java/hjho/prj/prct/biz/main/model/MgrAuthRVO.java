package hjho.prj.prct.biz.main.model;

import lombok.Data;

@Data
public class MgrAuthRVO {

	private String mgrId;
	
	private String mgrGrpId;
	
	private String menuId;
	
	private String menuNm;
	
	private String cretAuthYn;
	
	private String readAuthYn;
	
	private String updAuthYn;
	
	private String delAuthYn;
	
	private String exptAuthYn;
	
	private String privDataReadAuthYn;
	
}
