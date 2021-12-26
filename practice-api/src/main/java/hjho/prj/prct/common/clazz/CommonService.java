package hjho.prj.prct.common.clazz;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonService {

	@Autowired
	private RestTemplate restTemplate; 
	
	@Value("${url.practice-api}")
	private String API_URL;
	
	/**
	 *	RestTemplate Post EXCUTE 
	 */
	protected CommonMessage excutePost(String url, Object data) {
		
		CommonMessage msg = this.excute(HttpMethod.POST, url, data);
		
		return msg;
	}
	
	/**
	 *	RestTemplate Get EXCUTE 
	 */
	protected CommonMessage excuteGet(String url, Object data) {
		
		CommonMessage msg = this.excute(HttpMethod.GET, url, data);
		
		return msg;
	}
	
	private CommonMessage excute(HttpMethod method, String url, Object data) {
		
		// Set Header
		HttpHeaders header = this.initHeader(data);
		
		// Set URL
		String requestUrl = API_URL.concat(url);
		
		// Set Data
		HttpEntity<Object> entity = null;
		if(HttpMethod.POST.equals(method)) {
			entity = new HttpEntity<Object>(data, header); 
			
		} else {
			requestUrl = requestUrl.concat(this.initParam(data));
			entity = new HttpEntity<Object>(header);
		}
		
		ResponseEntity<CommonMessage> rspnData = restTemplate.exchange(requestUrl, method, entity, CommonMessage.class);
		
		return rspnData.getBody();
	}
	
	private HttpHeaders initHeader(Object data) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		if(data instanceof CommonModel) {
			CommonModel headerVO = (CommonModel) data;
			header.add("functionYn", headerVO.getFunctionYn());
		}
		return header;
	}
	
	@SuppressWarnings("unchecked")
	private String initParam(Object data) {
		if(ObjectUtils.isEmpty(data)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Map<String, Object> map = new ObjectMapper().convertValue(data, HashMap.class);
		sb.append("?");
		for (String key : map.keySet()) {
			sb.append(key);
			sb.append("=");
			sb.append(map.get(key));
			sb.append("&");
		}
		return sb.substring(0, (sb.length()-1)).toString();
	}
}
