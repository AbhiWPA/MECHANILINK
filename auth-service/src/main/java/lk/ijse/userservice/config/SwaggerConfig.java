package lk.ijse.userservice.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Title: mechani-link
 * Description: SwaggerConfig Class
 * Created by Abhishek Ashinsa on 6/13/2026
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MechaniLink Auth Service API")
                        .description("Authentication and User Management Service for MechaniLink Platform")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("MechaniLink Team")
                                .email("support@mechanilink.com")
                                .url("https://mechanilink.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .servers(List.of(
                        new Server().url("http://localhost:8085/api/users").description("Local Server"),
                        new Server().url("http://localhost:8085").description("Local Server (No Context)")
                ))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
