package cn.elvea.platform.commons.core.exception;

import cn.elvea.platform.commons.core.enums.ResponseCodeEnum;

/**
 * 业务异常
 *
 * @author elvea
 * @since 0.0.1
 */
public class BusinessException extends RuntimeException {

    public ResponseCodeEnum responseCode;

    public BusinessException(ResponseCodeEnum responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }


}
