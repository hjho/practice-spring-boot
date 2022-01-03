package hjho.prj.prct.biz.place.model;

import hjho.prj.prct.biz.table.Regions;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="CuntMgVO", description="국가 관리 VO")
public class PlaceRegiMgVO extends Regions {

	private static final long serialVersionUID = 1L;

}
