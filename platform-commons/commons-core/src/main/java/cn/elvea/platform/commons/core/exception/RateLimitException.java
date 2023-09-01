package cn.elvea.platform.commons.core.exception;

/**
 * 限流异常
 *
 * @author elvea
 * @since 0.0.1
 */
public class RateLimitException extends RuntimeException {

    public RateLimitException() {
    }

    public RateLimitException(String message) {
        super(message);
    }

    public RateLimitException(String message, Throwable cause) {
        super(message, cause);
    }

}
