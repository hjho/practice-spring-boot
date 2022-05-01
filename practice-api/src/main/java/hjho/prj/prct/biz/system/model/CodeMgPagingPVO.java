package hjho.prj.prct.biz.system.model;

import hjho.prj.prct.common.clazz.PagingModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@ApiModel(value="CodeMgPagingPVO", description="시스템 공통코드 관리 페이징 INPUT")
public class CodeMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="commCdNm", value="공통코드 명")
	private String commCdNm;
	
	@ApiModelProperty(name="useYn", value="사용여부")
	private String useYn;
	
}
