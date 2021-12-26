package hjho.prj.prct.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile({"loc", "dev"})
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Value( "${spring.profiles}" )
	private String springProfile;
	
	@Bean 
	public Docket swaggerDocket() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		
		docket
			.select()
			.apis(RequestHandlerSelectors.basePackage("hjho.prj.prct.biz"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(this.swaggerInfo());
		
		return docket;
	}
	
	private ApiInfo swaggerInfo() {
		final ApiInfoBuilder builder = new ApiInfoBuilder();
		String profile = springProfile;
		if("loc".equals(profile)) {
			profile = "(LOC)";
		} else if("dev".equals(profile)) {
			profile = "(DEV)";
		}
		builder
			.title("Swagger Documentation")
			.version("1.0")
			.license("c) Copyright HJHO PRACTICE ".concat(profile))
			.description("The api provides a playform to query build swagger api");
			
		return builder.build();
	}
	
}
