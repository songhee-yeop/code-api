package config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi kdshopApi() {
        Info info = new Info()
                .title("code API")
                .version("v1")
                .description("코드 정의를 하는 곳입니다.");

        OpenApiCustomiser openApiCustomiser = (openAPI) -> openAPI.info(info);

        String[] paths = {"/**"};

        return GroupedOpenApi.builder().group("코드 API").pathsToMatch(paths).addOpenApiCustomiser(openApiCustomiser)
                .build();
    }
}
