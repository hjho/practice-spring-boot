package hjho.prj.prct.biz.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="SelectBoxRVO", description="Select Box RVO")
public class SelectBoxRVO {

	@ApiModelProperty(name="commCdId", value="공통코드 아이디")
	private String commCdId;
	
	@ApiModelProperty(name="commCdNm", value="공통코드 명")
	private String commCdNm;
	
	@ApiModelProperty(name="cdVal", value="코드값")
	private String cdVal;
	
	@ApiModelProperty(name="cdValNm", value="코드값 명")
	private String cdValNm;
	
	@ApiModelProperty(name="addtCdNm", value="부속코드 명")
	private String addtCdNm;
	
	@ApiModelProperty(name="addtCdVal", value="부속코드 값")
	private String addtCdVal;

}
