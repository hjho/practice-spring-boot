package hjho.prj.prct.biz.main.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
public class MenuAuthPVO {
	
	private String mgrGrpId;
	
	private String menuId;

}
