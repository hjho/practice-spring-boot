package hjho.prj.prct.common.clazz;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommonModel implements Serializable {
	
	private static final long serialVersionUID = -4390504282993556643L;
	
	private String functionYn;
	
	private String cretSysId;
	
	private String cretMgrId;
	
	private String cretDtm;
	
	private String updSysId;
	
	private String updMgrId;

	private String updDtm;
	
}
