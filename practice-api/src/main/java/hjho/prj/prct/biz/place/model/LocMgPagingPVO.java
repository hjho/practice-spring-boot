package hjho.prj.prct.biz.place.model;

import hjho.prj.prct.common.clazz.PagingModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="LocMgPagingPVO", description="위치 관리 페이징 INPUT")
public class LocMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;

}
