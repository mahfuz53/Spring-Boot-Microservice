/*
package com.mahfuz.api_gateway;

import org.springframework.cloud.gateway.server.mvc.config.GatewayMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<?> quizServiceRoute() {
        return RouterFunctions
                .route(GET("/quiz/**"), request ->
                        request.toBuilder()
                                .uri("http://localhost:8090") // quiz-service URL
                                .build());
    }

    @Bean
    public RouterFunction<?> questionServiceRoute() {
        return RouterFunctions
                .route(GET("/question/**"), request ->
                        request.toBuilder()
                                .uri("http://localhost:8080") // question-service URL
                                .build());
    }
}*/
