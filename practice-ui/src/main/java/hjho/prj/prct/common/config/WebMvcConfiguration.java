package hjho.prj.prct.common.config;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

import hjho.prj.prct.common.filter.HtmlCharacterEscapes;
import hjho.prj.prct.common.filter.MethodWebFilter;
import hjho.prj.prct.common.filter.ParameterResolver;
import hjho.prj.prct.common.handler.HttpServletInterceptor;
import hjho.prj.prct.common.util.SessionUtil;
import hjho.prj.prct.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ComponentScan(basePackages= "hjho.prj.prct.biz") 
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Autowired
	private HttpServletInterceptor httpInterceptor;
	
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	// 예외 URL
	private final static String[] EXCLUDE_PATHS = {
			"/js/**",				// static
			"/images/**",			// static
			"/html/**",				// static
			"/css/**",				// static
			"/login/**",			// login page
			"/error/**",			// error page
			"/favicon.*"
	};
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.debug("[C] {} ##", StringUtil.RPAD("addResourceHandlers", 32, ""));
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		log.debug("[C] {} ##", StringUtil.RPAD("addArgumentResolvers", 32, ""));
		argumentResolvers.add(new ParameterResolver());
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurtr) {
		log.debug("[C] {} ##", StringUtil.RPAD("configureDefaultServletHandling", 32, ""));
		configurtr.enable();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.debug("[C] {} ##", StringUtil.RPAD("addInterceptors", 32, ""));
		// Servlet Interceptor
		registry.addInterceptor(httpInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns(Arrays.asList(EXCLUDE_PATHS));
		
		// Message Interceptor
		registry.addInterceptor(localeChangeInterceptor);
	}
	
	// HTML Filter
	@Bean
	public MappingJackson2HttpMessageConverter jsonEscapeConveter() {
		log.debug("[C] {} ##", StringUtil.RPAD("jsonEscapeConveter", 32, ""));
		ObjectMapper copyMapper = objectMapper.copy();
		copyMapper.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());
		return new MappingJackson2HttpMessageConverter(copyMapper);
	}
	
	// Method Filter
	@Bean 
	public FilterRegistrationBean<MethodWebFilter> getFilterRegistrationBean() {
		log.debug("[C] {} ##", StringUtil.RPAD("MethodFilter", 32, ""));
		FilterRegistrationBean<MethodWebFilter> registrationBean = new FilterRegistrationBean<MethodWebFilter>();
		registrationBean.setFilter(new MethodWebFilter());
		registrationBean.setOrder(1);
		registrationBean.addUrlPatterns("/*");
		registrationBean.setEnabled(false);
		return registrationBean;
	}
	
	// JSON VIEW
	@Bean
	public MappingJackson2JsonView jsonView() {
		log.debug("[C] {} ##", StringUtil.RPAD("jsonView", 32, ""));
		return new MappingJackson2JsonView();
	}
	
	// 메시지 소스 XML 파싱
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		log.debug("[C] {} ##", StringUtil.RPAD("messageSource", 32, ""));
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/i18n/message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	// MessageSourceAccessor
	@Bean
	public MessageSourceAccessor messageSourceAccessor() {
		log.debug("[C] {} ##", StringUtil.RPAD("messageSourceAccessor", 32, ""));
		return new MessageSourceAccessor(messageSource());
	}
	
	// 메세지 다국어 처리 인터셉터
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		log.debug("[C] {} ##", StringUtil.RPAD("localeChangeInterceptor", 32, ""));
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	
	// 메세지 다국어 처리
	@Bean
	public LocaleResolver sessionlocaleResolver() {
		log.debug("[C] {} ##", StringUtil.RPAD("sessionlocaleResolver", 32, ""));
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.KOREAN);
		return localeResolver;
	}
	
	@Bean
	public HttpSessionListener httpSessionListener(){
		log.debug("[C] {} ##", StringUtil.RPAD("httpSessionListener", 32, ""));
		return new SessionUtil();
	}
	
}
