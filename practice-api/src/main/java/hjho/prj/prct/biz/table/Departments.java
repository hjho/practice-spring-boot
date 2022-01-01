package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Departments extends CommonModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiParam(name="departmentId", value="부서 번호")
	private Long   departmentId;
	
	@ApiParam(name="departmentName", value="부서 명")
	private String departmentName;
	
	@ApiParam(name="managerId", value="부서장 아이디")
	private Long   managerId;
	
	@ApiParam(name="locationId", value="지역 아이디")
	private Long   locationId;
	
}
