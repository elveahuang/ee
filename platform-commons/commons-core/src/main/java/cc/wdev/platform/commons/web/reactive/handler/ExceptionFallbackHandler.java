package cc.wdev.platform.commons.web.reactive.handler;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

/**
 * 网关异常降级处理器
 *
 * @author elvea
 */
@Slf4j
public class ExceptionFallbackHandler implements HandlerFunction<ServerResponse> {

    @NonNull
    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        Optional<Object> originalUris = serverRequest.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        originalUris.ifPresent(originalUri -> log.error("Gateway [{}] fallback.", originalUri));

        JSONObject obj = new JSONObject();
        obj.put("code", HttpStatus.SERVICE_UNAVAILABLE.value());
        obj.put("messageId", HttpStatus.SERVICE_UNAVAILABLE.value());
        obj.put("message", "Service busy, please try again later.");
        obj.put("successful", false);
        obj.put("timestamp", System.currentTimeMillis());

        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(obj));
    }

}
