package hjho.prj.prct.biz.global.model;

import hjho.prj.prct.common.clazz.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class RegionsMgVO extends CommonModel {

	private static final long serialVersionUID = 1L;
	
	private Long   regionId;
	
	private String regionName;
	
}
