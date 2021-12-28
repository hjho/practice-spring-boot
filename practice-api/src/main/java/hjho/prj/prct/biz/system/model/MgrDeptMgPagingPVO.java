package hjho.prj.prct.biz.system.model;

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

//	@ApiParam(name="departmentId", value="부서 번호")
//	private long   departmentId;
	
	@ApiParam(name="departmentName", value="부서 명")
	private String departmentName;
	
//	@ApiParam(name="managerId", value="부서장 아이디")
//	private long   managerId;
//	
//	@ApiParam(name="managerName", value="부서장 이름")
//	private String managerName;
	
	@ApiParam(name="locationId", value="지역 아이디")
	private long   locationId;
	
//	@ApiParam(name="locationName", value="지역 명")
//	private String locationName;
	
}
