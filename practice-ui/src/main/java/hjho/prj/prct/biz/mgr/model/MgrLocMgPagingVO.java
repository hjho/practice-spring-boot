package hjho.prj.prct.biz.mgr.model;

import hjho.prj.prct.common.clazz.PagingModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class MgrLocMgPagingVO extends PagingModel {

	private static final long serialVersionUID = 1L;
	
	private String city;
	
	private String countryId;
}
