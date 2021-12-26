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
		log.debug("======= Request [Rest Template] ========");
		log.debug("=== Uri    : ({}) {}{}", request.getMethodValue(), request.getURI().getAuthority(), request.getURI().getPath());
		if("GET".equals(request.getMethodValue())) {
			log.debug("=== Query  : {}", request.getURI().getQuery());
		} else {
			log.debug("=== Body   : {}", new String(body).toString());
		}
		log.debug("========================================");
		
		ClientHttpResponse clientHttpResponse = execution.execute(request, body);
		
		log.debug("======= Response [Rest Template] =======");
		log.debug("=== Uri    : ({}) {}{}", request.getMethodValue(), request.getURI().getAuthority(), request.getURI().getPath());
		log.debug("=== Code   : {}", clientHttpResponse.getRawStatusCode());
		log.debug("=== Body   : {}", this.getBody(clientHttpResponse));
		log.debug("========================================");
		
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
