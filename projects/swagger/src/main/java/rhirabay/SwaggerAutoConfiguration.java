package rhirabay;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("rhirabay"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .protocols(Collections.singleton("https"))
                .apiInfo(new ApiInfo(
                        "springfoxサンプル",
                        "springfoxのサンプル",
                        "1.0.0",
                        null,
                        null,
                        null,
                        null,
                        Collections.emptyList()
                ))
                .globalResponses(HttpMethod.POST, Arrays.asList(
                        new ResponseBuilder()
                                .code("400")
                                .description("Bad Request")
                                .build()
                ));
    }
}