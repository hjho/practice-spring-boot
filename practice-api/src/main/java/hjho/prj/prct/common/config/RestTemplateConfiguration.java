package hjho.prj.prct.common.config;

import java.util.Collections;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import hjho.prj.prct.common.handler.RestTemplateErrorHandler;
import hjho.prj.prct.common.handler.RestTemplateInterceptor;
import hjho.prj.prct.common.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RestTemplateConfiguration {
	
	@Bean
	public RestTemplate restTemplate() {
		log.debug("[C] {} ##", StringUtil.RPAD("RestTemplateConfiguration", 32, ""));
		// HTTP CLIENT CREATE
		HttpClient httpClient = HttpClientBuilder.create()
										.setMaxConnTotal(30)
										.setMaxConnPerRoute(5)
										.build();
		
		// HTTP RESPONSE LIMIT
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		requestFactory.setConnectTimeout(30000);	// CONNEC TIME OUT
		requestFactory.setReadTimeout(30000); 		// READ TIME OUT
		
		// REST TEMPLATE LOG
		RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(requestFactory));
		restTemplate.setInterceptors(Collections.singletonList(new RestTemplateInterceptor()));
		
		// REST TEMPLATE ERROR
		restTemplate.setErrorHandler(new RestTemplateErrorHandler());
		
		return restTemplate;
	}
}
