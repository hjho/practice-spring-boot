package hjho.prj.prct.biz.main.model;

import java.util.List;
import java.util.Map;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="LoginPVO", description="로그인 검증 INPUT")
public class LoginPVO extends CommonModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="userId", value="아이디")
	private String userId;
	
	@ApiModelProperty(name="userPw", value="패스워드")
	private String userPw;
	
	@ApiModelProperty(name="userName", value="성명")
	private String userName;
	
	@ApiModelProperty(name="dtm", value="일시")
	private String dtm;
	
	@ApiModelProperty(name="num", value="숫자")
	private long   num;
	
	@ApiModelProperty(name="check", value="여부")
	private String check;
	
	@ApiModelProperty(name="objList", value="오브젝트 리스트")
	private List<Map<String, Object>> objList;
	
	@ApiModelProperty(name="objMap", value="오브젝트 맵")
	private Map<String, Object> objMap;
	
}
