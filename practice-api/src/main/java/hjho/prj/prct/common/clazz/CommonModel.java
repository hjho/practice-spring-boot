package hjho.prj.prct.common.clazz;

import java.io.Serializable;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class CommonModel implements Serializable {
	
	private static final long serialVersionUID = -4390504282993556643L;
	
	@ApiParam(name="functionYn", value="기능 여부", hidden=true)
	private String functionYn;
	
}
