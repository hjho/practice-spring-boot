package hjho.prj.prct.biz.sys.model;

import hjho.prj.prct.common.clazz.PagingModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class SysMenuMgPagingVO extends PagingModel {

	private static final long serialVersionUID = 1L;
	
	private String menuNm;
	
	private String useYn;
	
	private String hrMenuYn;
	
}
