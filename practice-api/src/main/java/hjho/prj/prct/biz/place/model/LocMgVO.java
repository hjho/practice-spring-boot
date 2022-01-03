package hjho.prj.prct.biz.place.model;

import hjho.prj.prct.biz.table.Locations;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="LocMgVO", description="위치 관리 INPUT")
public class LocMgVO extends Locations {

	private static final long serialVersionUID = 1L;

}
