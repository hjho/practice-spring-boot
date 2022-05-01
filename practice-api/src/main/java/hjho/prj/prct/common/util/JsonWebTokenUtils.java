package hjho.prj.prct.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import hjho.prj.prct.common.exception.UserException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JsonWebTokenUtils {
	
	@Value("${jwt.key}")
	private String JWT_KEY;

	private final boolean isLog = true;
	
	public static final String TK_ISSUER = "PRACTICE";
	public static final String ACCESS_TOKEN = "Access";
	public static final String REFRESH_TOKEN = "Refresh";

    // 토큰 생성
    public String createJWT(Object data) {
    	Date now = new Date(); 

        // SET HEADER
    	Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT_AUTH");
        headers.put("alg", "HS256");

        // SET PAY LOAD
        Map<String, Object> claims = new HashMap<>();
        claims.put("data", data);

        // 토큰 만료 시간
        Long expiredTime = 1000 * 15L * 60L;		// 토큰 유효 시간 (15분)  
        Date extTime = new Date(now.getTime() + expiredTime);
     
        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) 				// Headers 설정
                .setClaims(claims)					// Claims 설정
                .setSubject(ACCESS_TOKEN) 			// 토큰 용도 
                .setExpiration(extTime) 			// 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256	// HS256
                		, JWT_KEY.getBytes()) 		// Key로 Sign
                .setIssuer(TK_ISSUER)				// 발급자
                .compact(); 						// 토큰 생성

        return jwt;
    }
    
    // Refash 토큰 생성
    public String createRefreshJWT() {
    	Date now = new Date(); 
    	
    	// 토큰 만료 시간
    	Long expiredTime = 1000 * 60L * 60L * 24L;		// 토큰 유효 시간 (24시간)  
    	Date extTime = new Date(now.getTime() + expiredTime);
    	
    	// 토큰 Builder
    	String refreshJwt = Jwts.builder()
    							.setSubject(REFRESH_TOKEN) 	// 토큰 용도 
    							.setIssuer(TK_ISSUER) 		// 토큰 발급자
    							.setExpiration(extTime) 	// 토큰 만료 시간 설정
    							.signWith(SignatureAlgorithm.HS256, JWT_KEY.getBytes()) // HS256 과 Key로 Sign
    							.compact(); 				// 토큰 생성
    	
    	return refreshJwt;
    }
    
    // 토큰 검증
	@SuppressWarnings("unchecked")
	public Map<String, Object> verifyJWT(String jwt) throws UserException {
        Object data = null;
        String signature = null;
        try {
        	
        	Jws<Claims> jwtClaims = Jwts.parser()
        							.setSigningKey(JWT_KEY.getBytes())	// Set Key.
        							.requireSubject(ACCESS_TOKEN)		// 토큰 용도 검증.
        							.requireIssuer(TK_ISSUER)			// 토큰 발급자 검증.
        							.parseClaimsJws(jwt);				// 파싱 및 검증, 실패 시 에러
            
        	Claims claims = jwtClaims.getBody();
        	
        	Map<String, Object> header = jwtClaims.getHeader();
        	
        	signature = jwtClaims.getSignature();
        	
        	String subject = claims.getSubject();
        	String issuer = claims.getIssuer();
        	Date expired = claims.getExpiration();
        	data = claims.get("data");
        	
        	if(isLog) {
        		log.debug("[JWT] HEADER    : {}", header);
        		log.debug("[JWT] SIGNATURE : {}", signature);
        		// log.debug("[JWT] BODY      : {}", body);
        		log.debug("[JWT] DATA      : {}", data);
        		log.debug("[JWT] SUBJECT   : {}", subject);
        		log.debug("[JWT] ISSUER    : {}", issuer);
        		log.debug("[JWT] EXPIRED   : {}", DateUtil.getFormat(expired));
        	}
            
    	} catch (ExpiredJwtException eje) { 
    		log.warn("[JWT] 토큰 만료      : [{}]", eje.getMessage());
            throw new UserException("9004");
            /*    SignatureException - claimJws JWS 서명 검증이 실패한 경우.
             UnsupportedJwtException - claimJws 인수가 Claims JWS를 나타내지 않는 경우.
               MalformedJwtException - claimJws 문자열이 유효한 JWS가 아닌 경우
                 ExpiredJwtException - 지정된 JWT가 클레임 JWT이고 이 메서드가 호출되기 전에 클레임에 만료 시간이 있는 경우.
            IllegalArgumentException - claimJws 문자열이 null이거나 비어 있거나 공백만 있는 경우 */
    	} catch (IncorrectClaimException ice) {
    		log.warn("[JWT] 토큰 오류      : [{}]", ice.getMessage());
    		throw new UserException("9003", new String[] {"토큰 검증"});
    	}
        
        return VoUtil.objToMap(data);
    }    
	// Refresh 토큰 검증
	public boolean verifyRefreshJWT(String jwt) throws UserException {
		try {
			Jws<Claims> jwtClaims = Jwts.parser()
					.setSigningKey(JWT_KEY.getBytes())	// Set Key.
					.requireSubject(REFRESH_TOKEN)		// 토큰 용도 검증.
					.requireIssuer(TK_ISSUER)			// 토큰 발급자 검증.
					.parseClaimsJws(jwt);				// 파싱 및 검증, 실패 시 에러
			if(isLog) {
        		log.debug("[JWT] EXPIRED   : {}", DateUtil.getFormat(jwtClaims.getBody().getExpiration()));
        	}
		} catch (ExpiredJwtException eje) { 
			log.warn("[JWT] 토큰 만료      : [{}]", eje.getMessage());
			 throw new UserException("9004");
		} catch (IncorrectClaimException ice) {
			log.warn("[JWT] 토큰 검증 오류      : [{}]", ice.getMessage());
			throw new UserException("9003", new String[] {"토큰 검증"});
		} catch (Exception e) {
			log.warn("[JWT] 토큰 오류      : [{}]", e.getMessage());
			throw new UserException("9003", new String[] {"토큰 검증"});
		}
		
		return true;
	}    
    
}
