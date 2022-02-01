package hjho.prj.prct.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import hjho.prj.prct.common.exception.UserException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CryptoUtils {
	
	@Value("${crypto.algorithm}")
    private static String CRYPTO_ALGORITHM;
	@Value("${crypto.key}")
    private static String CRYPTO_KEY;
	@Value("${crypto.iv}")
	private static String CRYPTO_IV;
	
	@Value("${hmac.algorithm}")
	private static String HMAC_ALGORITHM;
	@Value("${hmac.key}")
	private static String HMAC_KEY;
	
    @Value("${jaspt.key}")
	private static String JASPT_KEY;
	@Value("${jaspt.algorithm}")
	private static String JASPT_ALGORITHM;
	
	private final static String DEFAULT_CHAR_SET = "UTF-8";
	
	/***********************************************************
	 * aesEncrypt
	 * @param decText
	 * @return
	 * @throws UserException
	 */
	public static String aesEncrypt(String decText) throws UserException {
		return aesEncrypt(decText, CryptoUtils.DEFAULT_CHAR_SET);
	}
	public static String aesEncrypt(String decText, String charSet) throws UserException {
		
		byte[] encrypted = null;
		
		try {
			
			Cipher cipher = Cipher.getInstance(CRYPTO_ALGORITHM);
			
			SecretKeySpec keySpec = new SecretKeySpec(CryptoUtils.get16Byte(CRYPTO_KEY, charSet), "AES");
			
			IvParameterSpec ivParamSpec = new IvParameterSpec(CryptoUtils.get16Byte(CRYPTO_IV, charSet));
			
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
			
			encrypted = cipher.doFinal(decText.getBytes(charSet));
			
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			log.warn("## 암호화 오류 : [{}]{}", e.getClass(), e.getMessage());
			throw new UserException("9003", new String[] {"암호화"});
		}
		
        return Base64.encodeBase64String(encrypted);
    }
	/******************************************************************
	 * aesDecrypt
	 * @param encText
	 * @return
	 * @throws UserException
	 */
	public static String aesDecrypt(String encText) throws UserException {
		return CryptoUtils.aesDecrypt(encText, CryptoUtils.DEFAULT_CHAR_SET);
	}
	public static String aesDecrypt(String encText, String charSet) throws UserException {
		
		String decText = null;
        try {
        	
			Cipher cipher = Cipher.getInstance(CRYPTO_ALGORITHM);
			
			SecretKeySpec keySpec = new SecretKeySpec(CryptoUtils.get16Byte(CRYPTO_KEY, charSet), "AES");
			
			IvParameterSpec ivParamSpec = new IvParameterSpec(CryptoUtils.get16Byte(CRYPTO_IV, charSet));
			
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

			byte[] decodedBytes = Base64.decodeBase64(encText);
			
			byte[] decrypted = cipher.doFinal(decodedBytes);
			
			decText = new String(decrypted, charSet);
			
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			log.warn("## 복호화 오류 : [{}]{}", e.getClass(), e.getMessage());
			throw new UserException("9003", new String[] {"복호화"});
		}
        
        return decText;
    }
	
	/******************************************************************
	 * shaEncrypt
	 * @param password
	 * @return
	 * @throws UserException
	 */
	public static String shaEncrypt(String password) throws UserException {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			
		} catch (NoSuchAlgorithmException e) {
			log.warn("## 암호화 오류 : [{}]{}", e.getClass(), e.getMessage());
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
	public static boolean shaCheck(String password, String shaPassword) throws UserException {
		String shaPwd = CryptoUtils.shaEncrypt(password);
		if(shaPwd.equals(shaPassword)) {
			return true;
		}
		return false;
	}

	/******************************************************************
	 * hmacBase64
	 * @param password
	 * @return
	 * @throws UserException
	 */
	public static String hmacBase64(String password) throws UserException {
		return CryptoUtils.hmacBase64(password, CryptoUtils.DEFAULT_CHAR_SET);
	}
	public static String hmacBase64(String password, String charSet) throws UserException {

		byte[] hash = null;
		
		try {
			SecretKeySpec secretKey = new SecretKeySpec(HMAC_KEY.getBytes(), HMAC_ALGORITHM);
			
			Mac hasher = Mac.getInstance(HMAC_ALGORITHM);
			
			hasher.init(secretKey);
			
			hash = hasher.doFinal(password.getBytes(charSet));
		} catch (NoSuchAlgorithmException | InvalidKeyException | IllegalStateException | UnsupportedEncodingException e) {
			log.warn("## 암호화 오류 : [{}]{}", e.getClass(), e.getMessage());
			throw new UserException("9003", new String[] {"암호화"});
		}
		
		return Base64.encodeBase64String(hash);
    }
	public static boolean hmacCheck(String password, String hmacPassword) throws UserException {
		String hmacPwd = CryptoUtils.hmacBase64(password);
		if(hmacPwd.equals(hmacPassword)) {
			return true;
		}
		return false;
	}
	/********************************************************
	 * jasyptEncoding
	 * @param value
	 * @return
	 */
	public static String jasyptEncoding(String value) {

        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setPassword(JASPT_KEY);
        pbeEnc.setAlgorithm(JASPT_ALGORITHM);
        
        return pbeEnc.encrypt(value);
    }
	
	private static byte[] get16Byte(String str, String charSet) throws UnsupportedEncodingException {
		if(StringUtils.isEmpty(str)) return null;
		
		String length16 = str;
		while(16 > length16.length()) {
			length16 = length16.concat(str);
		}
		length16 = length16.substring(0, 16);
		return length16.getBytes(charSet);
	}

}
