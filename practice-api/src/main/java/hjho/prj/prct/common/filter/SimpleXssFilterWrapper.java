package hjho.prj.prct.common.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleXssFilterWrapper extends HttpServletRequestWrapper {
	
	private boolean isLog = false;
	
	private InputStream input; 
	
	public SimpleXssFilterWrapper(HttpServletRequest request) {
		super(request);
		
		try {
			input = request.getInputStream();
		} catch (IOException e) {
			log.debug("[F] XSS Input Stream Error : {}", e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ServletInputStream getInputStream() throws IOException {
		if(input == null) return null;
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> returnMap = new HashMap<>();
		
		// ServletInputStream to Map
		Map<String, Object> bodyMap = mapper.readValue(IOUtils.toByteArray(this.input), HashMap.class);
		
		for (String key : bodyMap.keySet()) {
			Object obj = bodyMap.get(key);
			if(obj != null) {
				if(obj instanceof String) {
					String cleanVal = obj.toString();
					obj = SimpleXssFilter.cleanPatameter(key, cleanVal);
					// log.debug("[F] XSS InputStream String : {}", obj);
				} else
				if(obj instanceof List) {
					obj = this.getCleanList((List<Object>) obj);
					// log.debug("[F] XSS InputStream List : {}", obj);
				} else
				if(obj instanceof Map) {
					obj = this.getCleanMap((Map<String, Object>) obj);
					// log.debug("[F] XSS InputStream Map : {}", obj);
				}
			}
			returnMap.put(key, obj);
		}
		String output = mapper.writeValueAsString(returnMap);
		if(this.isLog) {
			log.debug("[F] XSS InputStream OutPut : {}", output);
		}
		
		// String to ServletInputStream
		ByteArrayInputStream bais = new ByteArrayInputStream(output.getBytes(StandardCharsets.UTF_8));
		ServletInputStream sis = new ServletInputStream() {
			@Override
			public int read() throws IOException {
				return bais.read(); 
			}
			@Override
			public void setReadListener(ReadListener listener) {}
			@Override
			public boolean isReady() { return false; }
			@Override
			public boolean isFinished() { return false; }
		};
		return sis;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] valuse = super.getParameterValues(name);
		
		if(valuse == null) return null;
		
		for(int i=0; i<valuse.length; i++) {
			valuse[i] = SimpleXssFilter.cleanPatameter(name, valuse[i]);
			if(this.isLog) {
				log.debug("[F] XSS Parameter Values : {}={} ", name, valuse[i]);
			}
		}
		return valuse;
	}
	
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		
		if(value == null) return null;
		
		value = SimpleXssFilter.cleanPatameter(name, value);
		if(this.isLog) {
			log.debug("[F] XSS Parameter : {}={} ", name, value);
		}
		return value;
	}
	
	@SuppressWarnings("unchecked")
	private List<Object> getCleanList(List<Object> param) {
		List<Object> list = new ArrayList<Object>();
		for (Object obj : param) {
			if(obj instanceof String) {
				String cleanVal = obj.toString();
				obj = SimpleXssFilter.cleanPatameter("list", cleanVal);
			} else
			if(obj instanceof Map) {
				obj = this.getCleanMap((Map<String, Object>) obj);
			}
			list.add(obj);
		}
		return list;
	}
	
	private Map<String, Object> getCleanMap(Map<String, Object> param) {
		Map<String, Object> map = param;
		for (String key : param.keySet()) {
			Object obj = param.get(key);
			if(obj != null && obj instanceof String) {
				String cleanVal = obj.toString();
				obj = SimpleXssFilter.cleanPatameter(key, cleanVal);
			}
			map.put(key, obj);
		}
		return map;
	}
	
}
