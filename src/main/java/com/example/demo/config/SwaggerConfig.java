package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Complaint Management API")
                        .version("1.0"))
                .servers(List.of(
                        // Replace the URL with your current workspace URL if it differs
                        new Server().url("https://9210.pro604cr.amypo.ai/")
                ));
    }
}