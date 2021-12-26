package hjho.prj.prct.common.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hjho.prj.prct.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class JasptConfiuration {
	
    @Value("${jaspt.key}")
	private String JASPT_KEY;
    
	@Value("${jaspt.algorithm}")
	private String JASPT_ALGORITHM;
	
	
 	@Bean(name = "jasyptEncryptor")
	public StringEncryptor jasyptEncryptor() {
 		log.debug("[C] {} ##", StringUtil.RPAD("JasptConfiuration", 32, ""));
	    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
	    config.setPassword(JASPT_KEY); 				// 암호화할 때 사용하는 키
	    config.setAlgorithm(JASPT_ALGORITHM); 		// 암호화 알고리즘
	    config.setKeyObtentionIterations("1000"); 	// 반복할 해싱 회수
	    config.setPoolSize("1"); 					// 인스턴스 pool
	    config.setProviderName("SunJCE");
	    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스
	    config.setStringOutputType("base64"); 		//인코딩 방식
	    
	    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
	    encryptor.setConfig(config);
	    
	    return encryptor;
	}
 	
}
