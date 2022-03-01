package hjho.prj.prct.common.clazz;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import hjho.prj.prct.common.util.SecurityUtil;

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
		return this.excute(HttpMethod.GET, url.concat("/box"), null).getData();
	}
	
	public static String getMethod(String requestURI) {
		String method = "";
		// 조회, 페이지 이동.
		if(requestURI.endsWith(CommonController.SEL) || requestURI.endsWith(CommonController.PAGE)) {
			method = "READ";
		// 등록
		} else if(requestURI.endsWith(CommonController.INS)) {
			method = "CRET";
		// 수정
		} else if(requestURI.endsWith(CommonController.UPD)) {
			method = "UPD";
		// 삭제
		} else if(requestURI.endsWith(CommonController.DEL)) {
			method = "DEL";
		// 개인정보 조회
		} else if(requestURI.endsWith(CommonController.PRIV)) {
			method = "PRIV";
		// 출력
		} else if(requestURI.endsWith(CommonController.EXPT)) {
			method = "EXPT";
		// 없음.
		} else {
			method = "";
		}
		return method;
	}
	public static String getPageURI(String requestURI) {
		// variable.substring(0, variable.indexOf("/put"));
		String pageURI = "";
		// 페이지 이동
		if(requestURI.endsWith(CommonController.PAGE)) {
			pageURI = requestURI.substring(0, requestURI.indexOf("/".concat(CommonController.PAGE)));
		// 조회
		} else if(requestURI.endsWith(CommonController.SEL)) {
			pageURI = requestURI.substring(0, requestURI.indexOf("/".concat(CommonController.SEL)));
		// 등록
		} else if(requestURI.endsWith(CommonController.INS)) {
			pageURI = requestURI.substring(0, requestURI.indexOf("/".concat(CommonController.INS)));
		// 수정
		} else if(requestURI.endsWith(CommonController.UPD)) {
			pageURI = requestURI.substring(0, requestURI.indexOf("/".concat(CommonController.UPD)));
		// 삭제
		} else if(requestURI.endsWith(CommonController.DEL)) {
			pageURI = requestURI.substring(0, requestURI.indexOf("/".concat(CommonController.DEL)));
		// 없음.
		} else {
			pageURI = "";
		}
		return pageURI;
	}
	
	public CommonMessage authCheck(HttpServletRequest request) {
		// Header
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.set("Authorization", "Bearer ".concat(SecurityUtil.getToken()));
		
		// Body
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("method"  , CommonService.getMethod(request.getRequestURI()));
		body.put("pageUrl" , CommonService.getPageURI(request.getRequestURI()));
		body.put("mgrId"   , SecurityUtil.getMgrInfo().getMgrId());
		body.put("mgrGrpId", SecurityUtil.getMgrInfo().getMgrGrpId());
		
		// Entity
		HttpEntity<Object> entity = new HttpEntity<Object>(body, header);
		
		// Run
		String authURI = API_URL.concat("/api/main/auth/mgr");
		ResponseEntity<CommonMessage> response = restTemplate.exchange(authURI, HttpMethod.POST, entity, CommonMessage.class);
		
		return response.getBody();
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
		// header.set("Authorization", "Bearer ".concat(SecurityUtil.getToken()));
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
		sb.append("?");
		if(data instanceof String) {
			sb.append(data);
			return sb.toString();
		}
		Map<String, Object> map = new ObjectMapper().convertValue(data, HashMap.class);
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
