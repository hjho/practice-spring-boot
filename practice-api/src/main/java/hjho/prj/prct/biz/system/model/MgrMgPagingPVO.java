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
@ApiModel(value="MgrMgPagingPVO", description="시스템 관리자 관리 페이징 INPUT")
public class MgrMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="mgrNm", value="관리자 이름")
	private String mgrNm;
	
	@ApiModelProperty(name="sysMgrStatCd", value="시스템 관리자 상태 코드")
	private String sysMgrStatCd;
	
}
