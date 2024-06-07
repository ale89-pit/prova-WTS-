package com.pit.cloudgateway.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoits = List.of("/api/auth/login");


    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoits
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
