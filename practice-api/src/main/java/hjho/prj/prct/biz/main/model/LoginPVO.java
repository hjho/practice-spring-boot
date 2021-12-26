package hjho.prj.prct.biz.main.model;

import java.util.List;
import java.util.Map;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class LoginPVO extends CommonModel {

	private static final long serialVersionUID = 1L;
	
	@ApiParam(name="userId", value="아이디")
	private String userId;
	
	@ApiParam(name="userPw", value="패스워드")
	private String userPw;
	
	@ApiParam(name="userName", value="성명")
	private String userName;
	
	@ApiParam(name="dtm", value="일시")
	private String dtm;
	
	@ApiParam(name="num", value="숫자")
	private long   num;
	
	@ApiParam(name="check", value="여부")
	private String check;
	
	@ApiParam(name="objList", value="오브젝트 리스트")
	private List<Map<String, Object>> objList;
	
	@ApiParam(name="objMap", value="오브젝트 맵")
	private Map<String, Object> objMap;
	
}
