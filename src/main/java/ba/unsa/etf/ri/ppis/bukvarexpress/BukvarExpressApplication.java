package ba.unsa.etf.ri.ppis.bukvarexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.RequestHandler;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Set;
import java.util.function.Predicate;

@SpringBootApplication
@EnableSwagger2
public class BukvarExpressApplication {

	public static void main(String[] args) {
		SpringApplication.run(BukvarExpressApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(customRequestHandlers())
				.build();
	}

	private Predicate<RequestHandler> customRequestHandlers() {
		return input -> {
			Set<RequestMethod> methods = input.supportedMethods();
			return methods.contains(RequestMethod.GET)
					|| methods.contains(RequestMethod.POST)
					|| methods.contains(RequestMethod.PUT)
					|| methods.contains(RequestMethod.DELETE);
		};
	}
}
