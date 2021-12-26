package hjho.prj.prct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySources({
    @PropertySource("classpath:private-${spring.profiles}.properties")
})
public class PracticeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApiApplication.class, args);
	}

}
