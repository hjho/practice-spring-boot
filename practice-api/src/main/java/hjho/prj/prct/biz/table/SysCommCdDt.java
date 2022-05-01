package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysCommCdDt extends CommonModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="commCdId", value="공통코드 아이디")
	private String commCdId;
	
	@ApiModelProperty(name="cdVal", value="코드값")
	private String cdVal;
	
	@ApiModelProperty(name="cdValNm", value="코드값 명")
	private String cdValNm;
	
	@ApiModelProperty(name="addtCdVal", value="부속코드 값")
	private String addtCdVal;
	
	@ApiModelProperty(name="sortOrd", value="정렬순서")
	private String sortOrd;
	
	@ApiModelProperty(name="useYn", value="사용여부")
	private String useYn;
	
}
