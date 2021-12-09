package com.example.websocketwithspringboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "admin-api", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(info = @Info(title = "WebSocket-With-Spring-Boot", version = "1.0", description = "WebSocket Application with Spring Boot"))
public class WebSocketWithSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketWithSpringBootApplication.class, args);
    }

}
