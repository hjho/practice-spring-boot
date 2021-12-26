package hjho.prj.prct.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import hjho.prj.prct.common.exception.UserException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CryptoUtils {
	
	@Value("${crupro.algorithm}")
    private String CRUPRO_ALGORITHM;
	@Value("${crupro.key}")
    private String CRUPRO_KEY;
	
    @Value("${jaspt.key}")
	private String JASPT_KEY;
	@Value("${jaspt.algorithm}")
	private String JASPT_ALGORITHM;
    
	public String aesEncrypt(String decText) throws UserException {
		
		byte[] encrypted = decText.getBytes();
		String IV = CRUPRO_KEY.substring(0, 16); 
		try {
			
			Cipher cipher = Cipher.getInstance(CRUPRO_ALGORITHM);
			SecretKeySpec keySpec = new SecretKeySpec(IV.getBytes(), "AES");
			IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
			
			encrypted = cipher.doFinal(decText.getBytes("UTF-8"));
			
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			log.warn("## 암호화 오류 : {}", e.getMessage());
			throw new UserException("9003", new String[] {"암호화"});
		}
		
        return Base64.getEncoder().encodeToString(encrypted);
    }

	public String aesDecrypt(String encText) throws UserException {
		
		String decText = encText;
		String IV = CRUPRO_KEY.substring(0, 16); 
        try {
			Cipher cipher = Cipher.getInstance(CRUPRO_ALGORITHM);
			SecretKeySpec keySpec = new SecretKeySpec(IV.getBytes(), "AES");
			IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

			byte[] decodedBytes = Base64.getDecoder().decode(encText);
			byte[] decrypted = cipher.doFinal(decodedBytes);
			decText = new String(decrypted, "UTF-8");
			
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			log.warn("## 복호화 오류 : {}", e.getMessage());
			throw new UserException("9003", new String[] {"복호화"});
		}
        
        return decText;
    }
	
	public String shaEncrypt(String password) throws UserException {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			
		} catch (NoSuchAlgorithmException e) {
			log.warn("## 암호화 오류 : {}", e.getMessage());
			throw new UserException("9003", new String[] {"암호화"});
		}
        md.update(password.getBytes());

        byte[] digest = md.digest();
        StringBuilder builder = new StringBuilder();
        for (byte b : digest) {
            builder.append(String.format("%02x", b));
        }
        
        return builder.toString();
    }

	public boolean shaCheck(String password, String shaPassword) throws UserException {
		String shaPwd = this.shaEncrypt(password);
		if(shaPwd.equals(shaPassword)) {
			return true;
		}
		return false;
	}
	
	public String jasyptEncoding(String value) {

        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setPassword(JASPT_KEY);
        pbeEnc.setAlgorithm(JASPT_ALGORITHM);
        
        return pbeEnc.encrypt(value);
    }
	
}
