package hjho.prj.prct.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class AuthVerifyException extends Exception {
	
	private static final long serialVersionUID = -3831286379466549813L;
	
	@Getter
	private String message;
	
	@Getter
	private HttpStatus status;
	
	public AuthVerifyException() {
		this.message = "권한이 없습니다.";
		this.status = HttpStatus.SERVICE_UNAVAILABLE;
	}
	
	public AuthVerifyException(String message){
		this.message = message;
		this.status = HttpStatus.SERVICE_UNAVAILABLE;
	}
	
	public AuthVerifyException(String message, HttpStatus status){
		this.message = message;
		this.status = status;
	}

}
