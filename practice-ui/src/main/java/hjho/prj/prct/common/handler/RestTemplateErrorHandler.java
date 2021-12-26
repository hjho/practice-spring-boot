package hjho.prj.prct.common.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestTemplateErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		
		HttpStatus statusCode = response.getStatusCode();
		
		return !(statusCode.is2xxSuccessful());
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		
		String errorContent = this.getErrorContent(response);
		log.debug("[H] RestTemplate Error Handler : {}", errorContent);
		
	}
	
	private String getErrorContent(ClientHttpResponse clientHttpResponse) throws IOException {
		try (
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											clientHttpResponse.getBody())) 
		) {
			return br.readLine();
		}
	}

}
