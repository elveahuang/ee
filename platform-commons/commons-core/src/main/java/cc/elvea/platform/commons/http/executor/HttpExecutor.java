package cc.elvea.platform.commons.http.executor;

import cc.elvea.platform.commons.http.ResponseHandler;

import java.io.IOException;

/**
 * 请求执行器
 *
 * @param <T>
 * @param <E>
 */
public interface HttpExecutor<T, E> {

    T execute(String uri, E data) throws IOException;

    void execute(String uri, E data, ResponseHandler<T> handler) throws IOException;

}
