package cn.elvea.platform.commons.core.exception;

/**
 * 系统异常
 *
 * @author elvea
 * @since 0.0.1
 */
public class SystemException extends RuntimeException {

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable throwable) {
        super(throwable);
    }

    public SystemException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
