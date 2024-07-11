package cc.elvea.platform.commons.http.executor;

import cc.elvea.platform.commons.http.ResponseHandler;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.Map;

/**
 * 请求执行器
 *
 * @param <T>
 * @param <E>
 */
public interface HttpExecutor<T, E> {

    default T execute(String url, E data) throws IOException {
        return this.execute(url, data, Maps.newHashMap());
    }

    T execute(String url, E data, Map<String, String> headerMap) throws IOException;

    default void execute(String url, E data, ResponseHandler<T> handler) throws IOException {
        this.execute(url, data, Maps.newHashMap(), handler);
    }

    void execute(String url, E data, Map<String, String> headerMap, ResponseHandler<T> handler) throws IOException;

}
