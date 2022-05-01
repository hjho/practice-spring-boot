package hjho.prj.prct.biz.main.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MenuAuthVO {

	@ApiModelProperty(name="authYn", value="권한 여부")
	private String authYn;
	
	@ApiModelProperty(name="menuId", value="메뉴 아이디")
	private String menuId;
	
	@ApiModelProperty(name="hrMenuId", value="상위 메뉴 아이디")
	private String hrMenuId;
	
	@ApiModelProperty(name="menuNm", value="메뉴 명")
	private String menuNm;
	
	@ApiModelProperty(name="pageUrl", value="페이지 URL")
	private String pageUrl;
	
	@ApiModelProperty(name="iconVal", value="아이콘값")
	private String iconVal;
	
	
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
