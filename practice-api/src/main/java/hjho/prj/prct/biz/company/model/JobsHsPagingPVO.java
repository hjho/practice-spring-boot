package hjho.prj.prct.biz.company.model;

import hjho.prj.prct.common.clazz.PagingModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@ApiModel(value="MgrJobHsMgPagingPVO", description="관리자 직책 히스토리 관리 페이징 INPUT")
public class JobsHsPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name="employeeId", value="사원번호")
	private Long   employeeId;
	
}
