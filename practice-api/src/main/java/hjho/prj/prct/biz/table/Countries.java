package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="Countries", description="국가 테이블 모델")
public class Countries extends CommonModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="countryId", value="국가 아이디")
	private String countryId;
	
	@ApiModelProperty(name="countryId", value="국가 명")
	private String countryName;
	
	@ApiModelProperty(name="regionId", value="대륙 번호")
	private Long   regionId;
	
}
