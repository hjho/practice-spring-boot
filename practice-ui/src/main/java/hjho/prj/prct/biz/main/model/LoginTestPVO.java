package hjho.prj.prct.biz.main.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class LoginTestPVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private String userId;
	
	private String userPw;
	
	private String userNm;
	
	private String mgrGrpId;
	
}
