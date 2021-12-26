package hjho.prj.prct.common.exception;

import lombok.Getter;

public class UserException extends Exception {
	
	private static final long serialVersionUID = -5709609560388036791L;
	
	@Getter
	private String code;
	@Getter
	private String message;
	@Getter
	private String[] args;
	
	public UserException(String code) {
		this.code = code;
	}
	public UserException(String code, String[] args) {
		this.code = code;
		this.args = args;
	}

}
