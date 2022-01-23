package hjho.prj.prct.common.clazz;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommonModel implements Serializable {
	
	private static final long serialVersionUID = -4390504282993556643L;
	
	@ApiModelProperty(name="functionYn", value="기능 여부", hidden=true)
	private String functionYn;
	
	@ApiModelProperty(name="cretSysId", value="등록 시스템 아이디", hidden=true)
	private String cretSysId;
	
	@ApiModelProperty(name="cretMgrId", value="등록 관리자 아이디", hidden=true)
	private String cretMgrId;
	
	@ApiModelProperty(name="cretDtm", value="등록 일시", hidden=true)
	private String cretDtm;
	
	@ApiModelProperty(name="updSysId", value="수정 시스템 아이디", hidden=true)
	private String updSysId;
	
	@ApiModelProperty(name="updMgrId", value="수정 관리자 아이디", hidden=true)
	private String updMgrId;
	
	@ApiModelProperty(name="updDtm", value="수정 일시", hidden=true)
	private String updDtm;
	
}
