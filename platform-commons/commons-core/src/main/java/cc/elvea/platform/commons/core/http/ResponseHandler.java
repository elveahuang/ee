package cc.elvea.platform.commons.core.http;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface ResponseHandler<T> {
    void handle(T t) throws Exception;
}
