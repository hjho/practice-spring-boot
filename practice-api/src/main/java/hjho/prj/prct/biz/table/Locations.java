package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(value="Locations", description="위치 테이블 모델")
public class Locations extends CommonModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="locationId", value="위치 번호")
	private Long   locationId;
	
	@ApiModelProperty(name="streetAddress", value="도로명")
	private String streetAddress;
	
	@ApiModelProperty(name="streetAddress", value="우편번호")
	private String postalCode;
	
	@ApiModelProperty(name="city", value="도시이름")
	private String city;
	
	@ApiModelProperty(name="stateProvince", value="주이름")
	private String stateProvince;
	
	@ApiModelProperty(name="countryId", value="국가 번호")
	private String countryId;
	
}
