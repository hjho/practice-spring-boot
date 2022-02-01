package hjho.prj.prct.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import hjho.prj.prct.common.exception.UserException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JsonWebTokenUtils {
	
	@Value("${jwt.key}")
	private String JWT_KEY;

    // 토큰 생성
    public String createJWT(Object data) {

        // SET HEADER
    	Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT_AUTH");
        headers.put("alg", "HS256");

        // SET PAY LOAD
        Map<String, Object> claims = new HashMap<>();
        claims.put("data", data);

        // 토큰 유효 시간 (2시간)
        Long expiredTime = 1000 * 60L * 60L * 2L;  
        // 토큰 만료 시간
        Date extTime = new Date(); 
        extTime.setTime(extTime.getTime() + expiredTime);
     
        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) 		// Headers 설정
                .setClaims(claims) 			// Claims 설정
                .setSubject("Authority") 	// 토큰 용도 
                .setExpiration(extTime) 	// 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, JWT_KEY.getBytes()) // HS256 과 Key로 Sign
                .compact(); 				// 토큰 생성

        return jwt;
    }
    
    // 토큰 검증
    public Map<String, Object> verifyJWT(String jwt) throws UserException {
        Map<String, Object> claimMap = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(JWT_KEY.getBytes()) 		// Set Key
                    .parseClaimsJws(jwt) 				  	// 파싱 및 검증, 실패 시 에러
                    .getBody();
            
            claimMap = body;

            JwsHeader<?> header = Jwts.parser()
                    .setSigningKey(JWT_KEY.getBytes()) 
                    .parseClaimsJws(jwt) 				  
                    .getHeader();
            String signature = Jwts.parser()
                    .setSigningKey(JWT_KEY.getBytes())
                    .parseClaimsJws(jwt) 				 
                    .getSignature();
            log.debug("## JWT HEADER    : " + header);
            log.debug("## JWT BODY      : " + body);
            log.debug("## JWT SIGNATURE : " + signature);
            
    	} catch (ExpiredJwtException eje) { 
    		log.warn("##### VerifyJWT 토큰이 만료되었을 경우 [{}]", eje.getMessage());
            throw new UserException("9004");
    	} 
        
        return claimMap;
    }    
    
}
