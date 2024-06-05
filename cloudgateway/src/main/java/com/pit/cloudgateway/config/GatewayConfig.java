package com.pit.cloudgateway.config;

import com.pit.cloudgateway.security.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/api/auth/**")
                        .filters(f->f.filter(filter))
                        .uri("http://localhost:8081"))
                .route("commesse-service", r -> r.path("/api/commesse/**")
                        .filters(f->f.filter(filter))

                        .uri("http://localhost:8082"))
                .build();
    }
}
