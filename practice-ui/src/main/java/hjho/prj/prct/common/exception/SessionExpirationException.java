package hjho.prj.prct.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class SessionExpirationException extends Exception {
	
	private static final long serialVersionUID = -3831286379466549813L;
	
	@Getter
	private String message;
	
	@Getter
	private HttpStatus status;
	
	public SessionExpirationException() {
		this.message = "세션이 만료되었습니다.";
		this.status = HttpStatus.SERVICE_UNAVAILABLE;
	}
	
	public SessionExpirationException(String message){
		this.message = message;
		this.status = HttpStatus.SERVICE_UNAVAILABLE;
	}
	
	public SessionExpirationException(String message, HttpStatus status){
		this.message = message;
		this.status = status;
	}

}
