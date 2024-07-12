package cc.elvea.platform.commons.http.executor;

import cc.elvea.platform.commons.http.HttpRequestType;
import cc.elvea.platform.commons.http.ResponseHandler;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 请求执行器
 *
 * @param <T>
 * @param <E>
 */
public interface HttpExecutor<T, E> {

    default T execute(String uri, E data) throws Exception {
        return this.execute(uri, data, Maps.newHashMap());
    }

    T execute(String uri, E data, Map<String, String> headerMap) throws Exception;

    T execute(HttpRequestType type, String uri, E data, Map<String, String> headerMap) throws Exception;

    default void execute(String uri, E data, ResponseHandler<T> handler) throws Exception {
        this.execute(uri, data, Maps.newHashMap(), handler);
    }

    default void execute(String uri, E data, Map<String, String> headerMap, ResponseHandler<T> handler) throws Exception {
        handler.handle(this.execute(uri, data, headerMap));
    }

    default void execute(HttpRequestType type, String uri, E data, ResponseHandler<T> handler) throws Exception {
        handler.handle(this.execute(type, uri, data, Maps.newHashMap()));
    }

    default void execute(HttpRequestType type, String uri, E data, Map<String, String> headerMap, ResponseHandler<T> handler) throws Exception {
        handler.handle(this.execute(type, uri, data, headerMap));
    }

}
