package com.esi.apigateway.filter;


import static org.springframework.http.HttpStatus.FORBIDDEN;

import com.esi.apigateway.util.JwtUtil;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Component
public class AuthenticationFilter
    extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (RouteValidator.isSecured.test(exchange.getRequest())) {

                val authorizationHeader = exchange.getRequest()
                    .getHeaders().get(HttpHeaders.AUTHORIZATION);

                if (Objects.isNull(authorizationHeader) || authorizationHeader.isEmpty()) {
                    log.warn("{} accessed without authorization header",
                        exchange.getRequest().getPath());
                    throw new ResponseStatusException(FORBIDDEN, "No Authorization header");
                }

                String authHeader = authorizationHeader.get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    JwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    log.warn("{} accessed with invalid authorization header {}",
                        exchange.getRequest().getPath(), authHeader);
                    throw new ResponseStatusException(FORBIDDEN, "Invalid authorization token");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
    }
}