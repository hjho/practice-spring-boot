package hjho.prj.prct.biz.company.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class LocationsMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;
	
	private Long   locationId;
	
	private String streetAddress;
	
	private String postalCode;
	
	private String city;
	
	private String stateProvince;
	
	private String countryId;
	
}
