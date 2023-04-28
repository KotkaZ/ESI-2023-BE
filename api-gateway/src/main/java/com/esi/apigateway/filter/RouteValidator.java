package com.esi.apigateway.filter;

import java.util.List;
import java.util.function.Predicate;
import org.springframework.http.server.reactive.ServerHttpRequest;

public class RouteValidator {
    private static final List<String> openApiEndpoints = List.of(
        "api/auth/authenticate",
        "api/auth/signup",
        "api/auth/login"
    );


    public static Predicate<ServerHttpRequest> isSecured =
        request -> openApiEndpoints
            .stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
