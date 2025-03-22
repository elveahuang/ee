package cc.elvea.platform.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

/**
 * @author elvea
 */
@Slf4j
public abstract class ReactiveUtils {

    public static Mono<Void> renderJson(@NonNull ServerHttpResponse response, Object object) throws Exception {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getType());
        DataBuffer dataBuffer = response.bufferFactory().wrap(JacksonUtils.toBytes(object));
        return response.writeWith(Mono.just(dataBuffer));
    }

}
