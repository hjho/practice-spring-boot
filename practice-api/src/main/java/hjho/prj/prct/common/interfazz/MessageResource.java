package hjho.prj.prct.common.interfazz;

import org.springframework.context.support.MessageSourceAccessor;

public interface MessageResource {
	
	/* Default User Message */ 
	public final String DEFAULT_SUCCESS_CODE = "0000";
	public final String DEFAULT_ERROR_CODE   = "9999";
	public final String DEFAULT_MESSAGE = "메시지 코드가 존재하지 않습니다.";
	
	public void setOk();
	
	public void setError();
	
	public void setXmlMessage(MessageSourceAccessor accessor);
	
}
