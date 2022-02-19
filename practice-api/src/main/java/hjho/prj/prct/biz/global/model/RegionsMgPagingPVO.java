package hjho.prj.prct.biz.global.model;

import hjho.prj.prct.common.clazz.PagingModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="CuntMgPagingPVO", description="국가 관리 페이징 INPUT")
public class RegionsMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="regionName", value="대륙 이름")
	private String regionName;
	
}
