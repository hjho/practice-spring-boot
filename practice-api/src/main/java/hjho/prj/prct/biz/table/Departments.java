package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="Departments", description="부서 테이블 모델")
public class Departments extends CommonModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="departmentId", value="부서 번호")
	private Long   departmentId;
	
	@ApiModelProperty(name="departmentName", value="부서 명")
	private String departmentName;
	
	@ApiModelProperty(name="managerId", value="부서장 아이디")
	private Long   managerId;
	
	@ApiModelProperty(name="locationId", value="지역 아이디")
	private Long   locationId;
	
}
