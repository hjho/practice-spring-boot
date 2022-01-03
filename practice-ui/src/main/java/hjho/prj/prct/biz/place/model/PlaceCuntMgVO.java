package hjho.prj.prct.biz.place.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class PlaceCuntMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;
	
	private String countryId;
	
	private String countryName;
	
	private Long   regionId;
	
}
