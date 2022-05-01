package hjho.prj.prct.biz.company.model;

import hjho.prj.prct.common.clazz.PagingModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="LocMgPagingPVO", description="위치 관리 페이징 INPUT")
public class LocationsMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="city", value="도시이름")
	private String city;
	
	@ApiModelProperty(name="countryId", value="국가 번호")
	private String countryId;
}
