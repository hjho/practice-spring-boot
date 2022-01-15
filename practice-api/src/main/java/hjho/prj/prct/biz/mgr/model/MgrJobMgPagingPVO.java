package hjho.prj.prct.biz.mgr.model;

import hjho.prj.prct.common.clazz.PagingModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@ApiModel(value="MgrJobMgPagingPVO", description="관리자 직책 관리 페이징 INPUT")
public class MgrJobMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name="jobTitle", value="직책 명")
	private String jobTitle;
	
}
