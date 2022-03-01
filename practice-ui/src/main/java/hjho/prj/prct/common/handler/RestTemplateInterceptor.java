package hjho.prj.prct.common.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
	throws IOException 
	{
		log.debug("=========== [Rest Template START] ============");
		if("GET".equals(request.getMethodValue())) {
			log.debug(">>>>> Request  : {}", request.getURI().getQuery());
		} else {
			log.debug(">>>>> Request  : {}", new String(body).toString());
		}
		log.debug("===== EXCUTE   : ({}) {}{}", request.getMethodValue(), request.getURI().getAuthority(), request.getURI().getPath());
		ClientHttpResponse clientHttpResponse = execution.execute(request, body);
		
		log.debug("<<<<< Response : ({}) {}", clientHttpResponse.getRawStatusCode(), this.getBody(clientHttpResponse));
		log.debug("=========== [Rest Template END  ] ============");
		
		return clientHttpResponse;
	}
	
	private String getBody(ClientHttpResponse clientHttpResponse) throws IOException {
		try (
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											clientHttpResponse.getBody())) 
		) {
			return br.readLine();
		}
	}

}
