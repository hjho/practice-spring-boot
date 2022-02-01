package hjho.prj.prct.biz.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.CryptoUtils;
import hjho.prj.prct.common.util.JsonWebTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/test")
@Api(tags="Test", value="TEST", description="테스트 용")
public class TestController {

	@Autowired
	private JsonWebTokenUtils jsonWebTokenUtils;
	
	@ApiOperation(value="aes decrypt", notes="AES 복호화 테스트", response=String.class)
	@GetMapping("/aes/decrypt")
	public CommonMessage aesDecrypt(@RequestParam String encText) throws UserException {
		CommonMessage message = new CommonMessage();
		log.debug("##### Aes Decrypt Parameter : {}", encText);
		
		String decText = CryptoUtils.aesDecrypt(encText);
		
		message.setOk();
		message.setData(decText);
		return message;
	}
	
	@ApiOperation(value="aes encrypt", notes="AES 암호화 테스트", response=String.class)
	@GetMapping("/aes/encrypt")
	public CommonMessage aesEncrypt(@RequestParam String decText) throws UserException {
		CommonMessage message = new CommonMessage();
		log.debug("##### Aes Encrypt Parameter : {}", decText);
		
		String encText = CryptoUtils.aesEncrypt(decText);
		
		message.setOk();
		message.setData(encText);
		return message;
	}
	
	@ApiOperation(value="sha encrypt", notes="SHA 암호화 테스트", response=String.class)
	@GetMapping("/sha/encrypt")
	public CommonMessage shaEncrypt(@RequestParam String decText) throws UserException {
		CommonMessage message = new CommonMessage();
		log.debug("##### Sha Encrypt Parameter : {}", decText);
		
		String encText = CryptoUtils.shaEncrypt(decText);
		
		message.setOk();
		message.setData(encText);
		return message;
	}
	
	@ApiOperation(value="sha check", notes="SHA 암호화 확인", response=Boolean.class)
	@GetMapping("/sha/check")
	public CommonMessage shaCheck(@RequestParam String password
			                    , @RequestParam String shaPassword) throws UserException {
		CommonMessage message = new CommonMessage();
		log.debug("##### Sha Check Parameter : {}, {}", password, shaPassword);
		
		boolean isOk = CryptoUtils.shaCheck(password, shaPassword);
		
		message.setOk();
		message.setData(isOk);
		return message;
	}
	@ApiOperation(value="hmac encrypt", notes="HMAC 암호화 테스트", response=String.class)
	@GetMapping("/hmac/encrypt")
	public CommonMessage hmacEncrypt(@RequestParam String decText) throws UserException {
		CommonMessage message = new CommonMessage();
		log.debug("##### HMac Encrypt Parameter : {}", decText);
		
		String encText = CryptoUtils.hmacBase64(decText);
		
		message.setOk();
		message.setData(encText);
		return message;
	}
	
	@ApiOperation(value="hmac check", notes="HMAC 암호화 확인", response=Boolean.class)
	@GetMapping("/hmac/check")
	public CommonMessage hmacCheck(@RequestParam String password, @RequestParam String shaPassword) throws UserException {
		CommonMessage message = new CommonMessage();
		log.debug("##### HMac Check Parameter : {}, {}", password, shaPassword);

		boolean isOk = CryptoUtils.hmacCheck(password, shaPassword);

		message.setOk();
		message.setData(isOk);
		return message;
	}
	
	@ApiOperation(value="enc jasypt", notes="JASYPT 암호화 테스트", response=String.class)
	@GetMapping("/jasypt/encrypt")
	public CommonMessage jasyptEcrypt(@RequestParam String decText) throws UserException {
		CommonMessage message = new CommonMessage();
		log.debug("##### Jasypt Encrypt Parameter : {}", decText);
		
		String encText = CryptoUtils.jasyptEncoding(decText);
		
		message.setOk();
		message.setData(encText);
		return message;
	}
	
	@ApiOperation(value="create jwt", notes="Json Web Token 생성 테스트", response=String.class)
	@GetMapping("/jwt/create")
	public CommonMessage createJwt(@RequestParam String data) throws UserException {
		CommonMessage message = new CommonMessage();
		log.debug("##### JWT Create Parameter : {}", data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("other", data);
		map.put("mgrId", "20220001");
		map.put("url", "/mgr/dept/page");
		map.put("CRET", "Y");
		map.put("READ", "Y");
		map.put("UPD", "Y");
		map.put("DEL", "Y");
		map.put("PRVIREAD", "Y");
		map.put("EXPROT", "Y");
		String jwt = jsonWebTokenUtils.createJWT(map);
		
		message.setOk();
		message.setData(jwt);
		return message;
	}
	
	@ApiOperation(value="verify jwt", notes="Json Web Token 검증 테스트", response=HashMap.class)
	@GetMapping("/jwt/verify")
	public CommonMessage verifyJwt(@RequestParam String jwt) throws UserException {
		CommonMessage message = new CommonMessage();
		log.debug("##### JWT Create Parameter : {}", jwt);
		
		Map<String, Object> data = jsonWebTokenUtils.verifyJWT(jwt);
		
		message.setOk();
		message.setData(data);
		return message;
	}
	
	@ApiOperation(value="base64 encode", notes="Base64 Encode 테스트", response=HashMap.class)
	@GetMapping("/base64/encode")
	public CommonMessage base64Encode(@RequestParam String str) throws UserException {
		CommonMessage message = new CommonMessage();
		log.debug("##### Base64 Encode Parameter : {}", str);
		
		String encode = Base64.encodeBase64String(str.getBytes());
		
		message.setOk();
		message.setData(encode);
		return message;
	}
	@ApiOperation(value="base64 decode", notes="Base64 Decode 테스트", response=HashMap.class)
	@GetMapping("/base64/decode")
	public CommonMessage base64Decode(@RequestParam String decodeStr) throws UserException {
		CommonMessage message = new CommonMessage();
		log.debug("##### Base64 Decode Parameter : {}", decodeStr);
		
		String decode = new String(Base64.decodeBase64(decodeStr.getBytes()));
		
		message.setOk();
		message.setData(decode);
		return message;
	}
	
}
