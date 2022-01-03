package hjho.prj.prct.biz.place.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class PlaceRegiMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;
	
	private Long   regionId;
	
	private String regionName;
	
}
