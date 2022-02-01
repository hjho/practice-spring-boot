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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CommonService {

	@Autowired
	private RestTemplate restTemplate; 
	
	@Value("${url.practice-api}")
	private String API_URL;
	
	public CommonMessage get(String url, Object data) {
		return this.excute(HttpMethod.GET, url, data);
	}
	public CommonMessage post(String url, Object data) {
		return this.excute(HttpMethod.POST, url, data);
	}
	public CommonMessage put(String url, Object data) {
		return this.excute(HttpMethod.PUT, url, data);
	}
	public CommonMessage delete(String url, Object data) {
		return this.excute(HttpMethod.DELETE, url, data);
	}
	public Object selectBox(String url) {
		return this.excute(HttpMethod.GET, url, null).getData();
	}
	
	private CommonMessage excute(HttpMethod method, String url, Object data) {
		// Set Header
		HttpHeaders header = this.initHeader(data);
		
		// Set URL
		String requestUrl = API_URL.concat(url);
		
		// Set Data
		HttpEntity<Object> entity = null;
		if(HttpMethod.GET.equals(method)) {
			requestUrl = requestUrl.concat(this.initParam(data));
			entity = new HttpEntity<Object>(header);
		} else {
			entity = new HttpEntity<Object>(data, header);
		}
		
		// Run
		ResponseEntity<CommonMessage> response = restTemplate.exchange(requestUrl, method, entity, CommonMessage.class);
		
		return response.getBody();
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
			if(map.get(key) != null) {
				if(map.get(key) instanceof String) {
					if("".equals((String) map.get(key))) {
						continue;
					}
				}
				sb.append(key);
				sb.append("=");
				sb.append(map.get(key));
				sb.append("&");
			}
		}
		return sb.substring(0, (sb.length()-1)).toString();
	}
	
}
