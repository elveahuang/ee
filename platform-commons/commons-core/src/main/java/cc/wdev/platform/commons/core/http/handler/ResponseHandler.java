package cc.wdev.platform.commons.core.http.handler;

/**
 * @author elvea
 */
public interface ResponseHandler<T> {
    void handle(T t) throws Exception;
}
