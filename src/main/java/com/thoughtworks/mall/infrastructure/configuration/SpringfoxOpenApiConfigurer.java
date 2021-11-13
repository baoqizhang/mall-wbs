package com.thoughtworks.mall.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SpringfoxOpenApiConfigurer {

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(info())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths((PathSelectors.ant("/error")
                        .or(PathSelectors.ant("/error/**/*"))
                        .or(PathSelectors.ant("/actuator"))
                        .or(PathSelectors.ant("/actuator/**/*"))).negate())
                .build();
    }

    private ApiInfo info() {
        return new ApiInfoBuilder()
                .title("mall document")
                .description("mall-wbs document")
                .version("1.0.0")
                .build();
    }
}
