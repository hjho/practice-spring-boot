package hjho.prj.prct.common.clazz;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommonModel implements Serializable {
	
	private static final long serialVersionUID = -4390504282993556643L;
	
	@ApiModelProperty(name="functionYn", value="기능 여부", hidden=true)
	private String functionYn;
	
}
