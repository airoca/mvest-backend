package mvest.core.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("https://www.mvest.o-r.kr"),
                        new Server().url("http://localhost:9000")
                ))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth", securityScheme()))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .info(apiInfo());
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .name("BearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }

    private Info apiInfo() {
        return new Info()
                .title("Mvest API Docs")
                .description("API documentation for Mvest")
                .version("1.0.0");
    }
}
