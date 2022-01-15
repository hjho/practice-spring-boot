package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="Jobs", description="직책 테이블 모델")
public class Jobs extends CommonModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="jobId", value="직책 아이디")
	private String jobId;
	
	@ApiModelProperty(name="jobTitle", value="직책 명")
	private String jobTitle;
	
	@ApiModelProperty(name="minSalary", value="최소 연봉")
	private Long   minSalary;
	
	@ApiModelProperty(name="maxSalary", value="최대 연봉")
	private Long   maxSalary;
	
}
