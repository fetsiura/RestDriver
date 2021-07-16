package pl.driver.upgrade.config;

import org.springframework.cache.annotation.EnableCaching;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
        import springfox.documentation.spi.service.contexts.SecurityContext;
        import springfox.documentation.spring.web.plugins.Docket;
        import springfox.documentation.swagger2.annotations.EnableSwagger2;

        import java.util.List;

        import static java.util.Collections.singletonList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Driver Api")
                .description("Advices to upgrade drivers skills")
                .version("1.0.0")
                .build();
    }


    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(UsernamePasswordAuthenticationToken.class)
                .select()
                .build().apiInfo(apiInfo()).select()
                .paths(PathSelectors.regex("^(?!/(error).*$).*$"))
                .apis(RequestHandlerSelectors.basePackage("pl.driver.upgrade"))
                .build()
                .securitySchemes(singletonList(createSchema()))
                .securityContexts(singletonList(createContext()));
    }

    private SecurityContext createContext() {
        return SecurityContext.builder()
                .securityReferences(createRef())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> createRef() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(new SecurityReference("apiKey", authorizationScopes));
    }

    private SecurityScheme createSchema() {
        return new ApiKey("apiKey", "Authorization", "header");
    }
}