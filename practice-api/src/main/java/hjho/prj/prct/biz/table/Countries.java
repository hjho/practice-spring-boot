package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Countries extends CommonModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiParam(name="countryId", value="국가 아이디")
	private String countryId;
	
	@ApiParam(name="countryId", value="국가 명")
	private String countryName;
	
	@ApiParam(name="regionId", value="대륙 번호")
	private Long   regionId;
	
}
