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
@ApiModel(value="MgrDeptMgPagingPVO", description="관리자 부서 관리 페이징 INPUT")
public class MgrDeptMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name="departmentName", value="부서 명")
	private String departmentName;
	
	@ApiModelProperty(name="countryId", value="국가 아이디")
	private String countryId;
	
}
