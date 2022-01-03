package hjho.prj.prct.biz.place.model;

import hjho.prj.prct.common.clazz.PagingModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="CuntMgPagingPVO", description="국가 관리 페이징 INPUT")
public class CuntMgPagingPVO extends PagingModel {

	private static final long serialVersionUID = 1L;

}
