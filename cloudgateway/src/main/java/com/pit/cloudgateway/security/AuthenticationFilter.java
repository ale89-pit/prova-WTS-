package com.pit.cloudgateway.security;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GatewayFilter {
    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

            if(routeValidator.isSecured.test(request)){
                if(this.isAuthMissing(request))
                    return this.onError(exchange, "Authorization header is " +
                            "missing in request", HttpStatus.UNAUTHORIZED);
                final String token = getAuthHeader(request);
                if(jwtUtil.isInvalid(token))
                    return this.onError(exchange, "Token is not valid", HttpStatus.FORBIDDEN);

                    this.populateRequestWithHeaders(exchange, token);
            }
        return chain.filter(exchange);
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("role",String.valueOf(claims.get("role")))
                .build();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0).split(
                " ")[1].trim();
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
            return !request.getHeaders().containsKey("Authorization");
    }
}
