package hjho.prj.prct.biz.main.model;

import java.util.List;
import java.util.Map;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class LoginVO extends CommonModel {

	private static final long serialVersionUID = 1L;

	private String userId;
	
	private String userPw;
	
	private String userName;
	
	private String dtm;
	
	private long   num;
	
	private String check;
	
	private List<Map<String, Object>> objList;
	
	private Map<String, Object> objMap;
	
}
