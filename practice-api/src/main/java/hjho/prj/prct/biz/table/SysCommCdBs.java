package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysCommCdBs extends CommonModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="commCdId", value="공통코드 아이디")
	private String commCdId;
	
	@ApiModelProperty(name="commCdNm", value="공통코드 명")
	private String commCdNm;
	
	@ApiModelProperty(name="commCdInfo", value="공통코드 정보")
	private String commCdInfo;
	
	@ApiModelProperty(name="useYn", value="사용여부")
	private String useYn;
	
	@ApiModelProperty(name="addtCdNm", value="부속코드 명")
	private String addtCdNm;
	
	@ApiModelProperty(name="addtCdUseYn", value="부속코드 사용 여부")
	private String addtCdUseYn;
	
}
