package cc.elvea.platform.commons.core.http.handler;

/**
 * @author elvea
 */
public interface ResponseHandler<T> {
    void handle(T t) throws Exception;
}
