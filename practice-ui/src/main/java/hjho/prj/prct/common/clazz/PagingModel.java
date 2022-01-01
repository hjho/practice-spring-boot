package hjho.prj.prct.common.clazz;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class PagingModel extends CommonModel {
	
	private static final long serialVersionUID = 1L;

	private Long   page;
	
	private Long   length;
	
	private Long   sortCol;
	
	private String sortType;
	
}
