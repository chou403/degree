package com.second.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/21
 * {@code @description} 对进来的请求header添加外网标识符: from=public
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    /**
     * Process the Web request and (optionally) delegate to the next {@code GatewayFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(
                exchange.mutate().request(
                                exchange.getRequest().mutate().header("id", "").header("from", "public").build())
                        .build()
        );
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
