package hjho.prj.prct.biz.main.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MgrAuthRVO {

	@ApiModelProperty(name="menuId", value="메뉴 아이디")
	private String menuId;
	
	@ApiModelProperty(name="cretAuthYn", value="등록 권한 여부")
	private String cretAuthYn;
	
	@ApiModelProperty(name="readAuthYn", value="조회 권한 여부")
	private String readAuthYn;
	
	@ApiModelProperty(name="updAuthYn", value="수정 권한 여부")
	private String updAuthYn;
	
	@ApiModelProperty(name="delAuthYn", value="삭제 권한 여부")
	private String delAuthYn;
	
	@ApiModelProperty(name="exptAuthYn", value="출력 권한 여부")
	private String exptAuthYn;
	
	@ApiModelProperty(name="privDataReadAuthYn", value="개인 정보 조회 권한 여부")
	private String privDataReadAuthYn;
	
}
