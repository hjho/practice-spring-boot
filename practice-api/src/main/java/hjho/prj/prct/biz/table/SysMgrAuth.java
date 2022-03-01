package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysMgrAuth extends CommonModel {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name="mgrGrpId", value="관리자 그룹 아이디")
	private String mgrGrpId;
	
	@ApiModelProperty(name="menuId", value="메뉴 아이디")
	private String menuId;
	
	@ApiModelProperty(name="readAuthYn", value="조회 권한 여부")
	private String readAuthYn;
	
	@ApiModelProperty(name="cretAuthYn", value="등록 권한 여부")
	private String cretAuthYn;
	
	@ApiModelProperty(name="updAuthYn", value="수정 권한 여부")
	private String updAuthYn;
	
	@ApiModelProperty(name="delAuthYn", value="삭제 권한 여부")
	private String delAuthYn;
	
	@ApiModelProperty(name="exptAuthYn", value="EXPT_AUTH_YN")
	private String exptAuthYn;
	
	@ApiModelProperty(name="privDataReadAuthYn", value="개인 정보 조회 권한 여부")
	private String privDataReadAuthYn;
	
	@ApiModelProperty(name="useYn", value="사용여부")
	private String useYn;
	
}
