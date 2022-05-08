package hjho.prj.prct.biz.test.model;

import hjho.prj.prct.common.interfazz.CryptoType;
import hjho.prj.prct.common.interfazz.CryptoType.Division;
import hjho.prj.prct.common.interfazz.CryptoType.Type;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="CryptoTestVO", description="Crypto Test VO")
public class CryptoTestVO {

	/* Encode */
	@CryptoType(dv = Division.ENC, tp = Type.AES)
	@ApiModelProperty(name="encAes", value="AES 암호화", example = "테스트")
	private String encAes;
	
	@CryptoType(dv = Division.ENC, tp = Type.URI)
	@ApiModelProperty(name="encUri", value="URI 암호화", example = "https://www.naver.com#test=테스트")
	private String encUri;
	
	@CryptoType(dv = Division.ENC, tp = Type.B64)
	@ApiModelProperty(name="encB64", value="Base64 암호화", example = "BASE64 테스트 입니다.")
	private String encB64;
	
	/* Decode */
	@CryptoType(dv = Division.DEC, tp = Type.AES)
	@ApiModelProperty(name="decAes", value="AES 복호화", hidden = true)
	private String decAes;
	
	@CryptoType(dv = Division.DEC, tp = Type.URI)
	@ApiModelProperty(name="decUri", value="URI 복호화", hidden = true)
	private String decUri;
	
	@CryptoType(dv = Division.DEC, tp = Type.B64)
	@ApiModelProperty(name="decB64", value="Base64 복호화", hidden = true)
	private String decB64;
	
	@ApiModelProperty(name="noneCt", value="없음", example = "없음")
	private String noneCt;
	
}
