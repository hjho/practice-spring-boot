package hjho.prj.prct.biz.test.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.test.mapper.TestMapper;
import hjho.prj.prct.biz.test.model.CryptoTestVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.CryptoUtils;
import hjho.prj.prct.common.util.JsonWebTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/test")
@Api(tags="Test", value="TEST", description="테스트 용")
public class TestController extends CommonController {

	@Autowired
	private JsonWebTokenUtils jsonWebTokenUtils;
	
	@Autowired
	private CryptoUtils cryptoUtils;
	
	@Autowired
	private TestMapper testMapper;
	
	@ApiOperation(value="AES 복호화 테스트", notes="/api/test/aes/decrypt", response=String.class)
	@GetMapping("/aes/decrypt")
	public CommonMessage aesDecrypt(@RequestParam String encText) throws UserException {
		super.parameterLog("Test[aesDecrypt]", encText);
		
		CommonMessage message = new CommonMessage();
		
		String decText = cryptoUtils.aesDecrypt(encText);
		
		message.setOk();
		message.setData(decText);
		return message;
	}
	
	@ApiOperation(value="AES 암호화 테스트", notes="/api/test/aes/encrypt", response=String.class)
	@GetMapping("/aes/encrypt")
	public CommonMessage aesEncrypt(@RequestParam String decText) throws UserException {
		super.parameterLog("Test[aesEncrypt]", decText);
		
		CommonMessage message = new CommonMessage();
		
		String encText = cryptoUtils.aesEncrypt(decText);
		
		message.setOk();
		message.setData(encText);
		return message;
	}
	
	@ApiOperation(value="SHA 암호화 테스트", notes="/api/test/sha/encrypt", response=String.class)
	@GetMapping("/sha/encrypt")
	public CommonMessage shaEncrypt(@RequestParam String decText) throws UserException {
		super.parameterLog("Test[shaEncrypt]", decText);
		CommonMessage message = new CommonMessage();
		
		String encText = cryptoUtils.shaEncrypt(decText);
		
		message.setOk();
		message.setData(encText);
		return message;
	}
	
	@ApiOperation(value="SHA 암호화 확인", notes="/api/test/sha/check", response=Boolean.class)
	@GetMapping("/sha/check")
	public CommonMessage shaCheck(@RequestParam String password
			                    , @RequestParam String shaPassword) throws UserException {
		super.parameterLog("Test[shaCheck]", new Object[] {password, shaPassword});
		CommonMessage message = new CommonMessage();
		
		boolean isOk = cryptoUtils.shaCheck(password, shaPassword);
		
		message.setOk();
		message.setData(isOk);
		return message;
	}
	@ApiOperation(value="HMAC 암호화 테스트", notes="/api/test/hmac/encrypt", response=String.class)
	@GetMapping("/hmac/encrypt")
	public CommonMessage hmacEncrypt(@RequestParam String decText) throws UserException {
		super.parameterLog("Test[hmacEncrypt]", decText);
		CommonMessage message = new CommonMessage();
		
		String encText = cryptoUtils.hmacBase64(decText);
		
		message.setOk();
		message.setData(encText);
		return message;
	}
	
	@ApiOperation(value="HMAC 암호화 확인", notes="/api/test/hmac/check", response=Boolean.class)
	@GetMapping("/hmac/check")
	public CommonMessage hmacCheck(@RequestParam String password, @RequestParam String shaPassword) throws UserException {
		super.parameterLog("Test[hmacCheck]", new Object[] {password, shaPassword});
		CommonMessage message = new CommonMessage();

		boolean isOk = cryptoUtils.hmacCheck(password, shaPassword);

		message.setOk();
		message.setData(isOk);
		return message;
	}
	
	@ApiOperation(value="JASYPT 암호화 테스트", notes="/api/test/jasypt/encrypt", response=String.class)
	@GetMapping("/jasypt/encrypt")
	public CommonMessage jasyptEcrypt(@RequestParam String decText) throws UserException {
		super.parameterLog("Test[jasyptEcrypt]", decText);
		CommonMessage message = new CommonMessage();
		
		String encText = cryptoUtils.jasyptEncoding(decText);
		
		message.setOk();
		message.setData(encText);
		return message;
	}
	
	@ApiOperation(value="Json Web Token 생성 테스트", notes="/api/test/jwt/create", response=String.class)
	@GetMapping("/jwt/create")
	public CommonMessage createJwt(@RequestParam String data) throws UserException {
		super.parameterLog("Test[createJwt]", data);
		CommonMessage message = new CommonMessage();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("other", data);
		map.put("mgrId", "20220001");
		map.put("url", "/mgr/dept/page");
		// C R U D 개인정보 출력
		map.put("role", new String[] {"Y", "Y", "Y", "Y", "N", "N"}); 
		String jwt = jsonWebTokenUtils.createJWT(map);
		
		message.setOk();
		message.setData(jwt);
		return message;
	}
	
	@ApiOperation(value="Json Web Refresh Token 생성 테스트", notes="/api/test/jwt/create/refresh", response=String.class)
	@GetMapping("/jwt/create/refresh")
	public CommonMessage createRefreshJwt() throws UserException {
		super.parameterLog("Test[createRefreshJwt]", "");
		CommonMessage message = new CommonMessage();
		
		String jwt = jsonWebTokenUtils.createRefreshJWT();
		
		message.setOk();
		message.setData(jwt);
		return message;
	}
	
	@ApiOperation(value="Json Web Token 검증 테스트", notes="/api/test/jwt/verify", response=HashMap.class)
	@GetMapping("/jwt/verify")
	public CommonMessage verifyJwt(@RequestParam String jwt) throws UserException {
		super.parameterLog("Test[verifyJwt]", jwt);
		CommonMessage message = new CommonMessage();
		
		Map<String, Object> data = jsonWebTokenUtils.verifyJWT(jwt);
		
		message.setOk();
		message.setData(data);
		return message;
	}
	@ApiOperation(value="Json Web Refresh Token 검증 테스트", notes="/api/test/jwt/verify/refresh", response=Boolean.class)
	@GetMapping("/jwt/verify/refresh")
	public CommonMessage verifyRefreshJwt(@RequestParam String jwt) throws UserException {
		super.parameterLog("Test[verifyRefreshJwt]", jwt);
		CommonMessage message = new CommonMessage();
		
		boolean isVerifyOk = jsonWebTokenUtils.verifyRefreshJWT(jwt);
		
		message.setOk();
		message.setData(isVerifyOk);
		return message;
	}
	
	@ApiOperation(value="Base64 Encode 테스트", notes="/api/test/base64/encode", response=String.class)
	@GetMapping("/base64/encode")
	public CommonMessage base64Encode(@RequestParam String str) throws UserException {
		super.parameterLog("Test[base64Encode]", str);
		CommonMessage message = new CommonMessage();
		
		String encode = Base64.getEncoder().encodeToString(str.getBytes());
		
		message.setOk();
		message.setData(encode);
		return message;
	}
	@ApiOperation(value="Base64 Decode 테스트", notes="/api/test/base64/decode", response=String.class)
	@GetMapping("/base64/decode")
	public CommonMessage base64Decode(@RequestParam String decodeStr) throws UserException {
		super.parameterLog("Test[base64Decode]", decodeStr);
		CommonMessage message = new CommonMessage();
		
		String decode = new String(Base64.getDecoder().decode(decodeStr.getBytes()));
		
		message.setOk();
		message.setData(decode);
		return message;
	}
	
	@ApiOperation(value="SQL ENCODE TEST", notes="/api/test/sql/crypto", response=String.class)
	@PostMapping("/sql/crypto")
	public CommonMessage sqlCrypto(@RequestBody CryptoTestVO cryptoTestVO) throws UserException {
		super.parameterLog("Test[CryptoTest Before]", cryptoTestVO);
		CommonMessage message = new CommonMessage();
		
		CryptoTestVO output = testMapper.crytoTest(cryptoTestVO);
		
		super.parameterLog("Test[CryptoTest After]", cryptoTestVO);
		message.setOk();
		message.setData(output);
		return message;
	}
	
}
