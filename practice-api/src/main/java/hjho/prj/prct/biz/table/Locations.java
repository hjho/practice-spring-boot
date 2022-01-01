package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Locations extends CommonModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiParam(name="locationId", value="지역 번호")
	private Long   locationId;
	
	@ApiParam(name="streetAddress", value="도로명")
	private String streetAddress;
	
	@ApiParam(name="streetAddress", value="우편번호")
	private String postalCode;
	
	@ApiParam(name="city", value="도시이름")
	private String city;
	
	@ApiParam(name="stateProvince", value="주이름")
	private String stateProvince;
	
	@ApiParam(name="countryId", value="국가 번호")
	private String countryId;
	
}
