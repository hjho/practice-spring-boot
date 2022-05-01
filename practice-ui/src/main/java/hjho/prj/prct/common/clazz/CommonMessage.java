package hjho.prj.prct.common.clazz;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;

import hjho.prj.prct.common.interfazz.MessageResource;
import lombok.Data;

@Data
public class CommonMessage implements MessageResource {

	private String code;
	
	private String message;
	
	private Object data;
	
	private String[] args;

	public boolean isSuccess() {
		return DEFAULT_SUCCESS_CODE.equals(this.code);
	}
	
	@Override
	public void setOk() {
		this.code = DEFAULT_SUCCESS_CODE;
	}
	
	@Override
	public void setError() {
		this.code = DEFAULT_ERROR_CODE;
	}
	
	@Override
	public void setXmlMessage(MessageSourceAccessor accessor) {
		this.message = this.getXmlMessage(accessor);
	}
	
	private String getXmlMessage(MessageSourceAccessor accessor) {
		return accessor.getMessage(this.getCode(), this.getArgs(), DEFAULT_MESSAGE, LocaleContextHolder.getLocale());
	}

}

