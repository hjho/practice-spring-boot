package hjho.prj.prct.common.clazz;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class PagingModel extends CommonModel {
	
	private static final long serialVersionUID = 1L;

	private long   rowNo;
	
	private long   page;
	
	private long   length;
	
	private long   totalCnt;
	
	private long   sortCol;
	
	private String sortType;
	
}
