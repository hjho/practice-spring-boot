package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="Regions", description="대륙 테이블 모델")
public class Regions extends CommonModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="regionId", value="대륙 번호")
	private Long   regionId;
	
	@ApiModelProperty(name="regionId", value="대륙 명")
	private String regionName;
	
}
