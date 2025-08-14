package top.baihu.platform.commons.core.http.executor;

import com.google.common.collect.Maps;
import top.baihu.platform.commons.core.http.enums.HttpRequestTypeEnum;
import top.baihu.platform.commons.core.http.handler.ResponseHandler;

import java.util.Map;

/**
 * 请求执行器
 *
 * @param <T> 返回值类型
 * @param <E> 参数类型
 */
public interface HttpExecutor<T, E> {

    default T execute(String uri, E data) throws Exception {
        return this.execute(uri, data, Maps.newHashMap());
    }

    T execute(String uri, E data, Map<String, String> headerMap) throws Exception;

    T execute(HttpRequestTypeEnum type, String uri, E data, Map<String, String> headerMap) throws Exception;

    default void execute(String uri, E data, ResponseHandler<T> handler) throws Exception {
        this.execute(uri, data, Maps.newHashMap(), handler);
    }

    default void execute(String uri, E data, Map<String, String> headerMap, ResponseHandler<T> handler) throws Exception {
        handler.handle(this.execute(uri, data, headerMap));
    }

    default void execute(HttpRequestTypeEnum type, String uri, E data, ResponseHandler<T> handler) throws Exception {
        handler.handle(this.execute(type, uri, data, Maps.newHashMap()));
    }

    default void execute(HttpRequestTypeEnum type, String uri, E data, Map<String, String> headerMap, ResponseHandler<T> handler) throws Exception {
        handler.handle(this.execute(type, uri, data, headerMap));
    }

}
