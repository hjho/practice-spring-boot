package hjho.prj.prct.biz.mgr.model;

import hjho.prj.prct.common.clazz.PagingModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class MgrDeptMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;

	@ApiParam(name="departmentName", value="부서 명")
	private String departmentName;
	
	@ApiParam(name="countryId", value="국가 아이디")
	private String countryId;
	
}
