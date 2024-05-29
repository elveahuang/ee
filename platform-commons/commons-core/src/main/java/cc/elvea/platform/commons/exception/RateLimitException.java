package cc.elvea.platform.commons.exception;

/**
 * 限流异常
 *
 * @author elvea
 * @since 24.1.0
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
