package kr.co.koscom.oppfm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan(basePackages = "kr.co.koscom.oppfm")
@EnableWebMvc
@EnableSwagger2
@Import(SwaggerUiConfiguration.class)
public class SwaggerConfig {
    
    @Bean
    ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
        "Koscom Mobile Open API",
        "Koscom Mobile Open API...",
        "1.0.0",
        "",
        "",
        "Creative Commons 4.0 International",
        "http://creativecommons.org/licenses/by/4.0/" );
        return apiInfo;
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("kr.co.koscom.oppfm"))
                .build()
                .apiInfo(apiInfo());
    }

}