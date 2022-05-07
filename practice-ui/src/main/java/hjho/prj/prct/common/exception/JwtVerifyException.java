package hjho.prj.prct.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class JwtVerifyException extends Exception {
	
	private static final long serialVersionUID = -3831286379466549813L;
	
	@Getter
	private String message;
	
	@Getter
	private HttpStatus status;
	
	public JwtVerifyException() {
		this.message = "유효하지 않은 토큰입니다.";
		this.status = HttpStatus.SERVICE_UNAVAILABLE;
	}
	
	public JwtVerifyException(String message){
		this.message = message;
		this.status = HttpStatus.SERVICE_UNAVAILABLE;
	}
	
	public JwtVerifyException(String message, HttpStatus status){
		this.message = message;
		this.status = status;
	}

}
