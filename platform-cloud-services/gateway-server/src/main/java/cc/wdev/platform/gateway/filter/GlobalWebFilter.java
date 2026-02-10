package cc.wdev.platform.gateway.filter;

import cc.wdev.platform.commons.core.tenant.TenantContext;
import cc.wdev.platform.commons.utils.mdc.MdcContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static cc.wdev.platform.commons.constants.GlobalConstants.REQUEST_ID_KEY;

/**
 * @author elvea
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GlobalWebFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("[GlobalWebFilter] doFilter Start");

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        try {
            // 初始化MDC上下文
            log.info("[GlobalWebFilter] Init MDC Context");
            MdcContext.handleReactiveRequest(request, response);
            log.info("[GlobalWebFilter] MDC Context {}", MdcContext.getRequestId());

            // 初始化租户上下文
            log.info("[GlobalWebFilter] Init Tenant Context");
            TenantContext.handleReactiveRequest(request, response);

            ServerHttpRequest wr = exchange.getRequest().mutate().header(REQUEST_ID_KEY, MdcContext.getRequestId()).build();
            return chain.filter(exchange.mutate().request(wr).build());
        } finally {
            log.info("[GlobalWebFilter] Clear Tenant Context");
            TenantContext.clear();

            log.info("[GlobalWebFilter] Clear MDC Context");
            TenantContext.clear();
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
