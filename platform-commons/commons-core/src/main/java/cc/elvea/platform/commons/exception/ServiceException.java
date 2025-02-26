package cc.elvea.platform.commons.exception;

import cc.elvea.platform.commons.enums.ResponseCodeEnum;
import com.aliyun.tea.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Map;

/**
 * 服务异常
 *
 * @author elvea
 */
@Getter
@Setter
public class ServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResponseCodeEnum responseCode = ResponseCodeEnum.ERROR;

    public String code;

    public String message;

    public Map<String, Object> data;

    public Integer statusCode;

    public String description;

    public ServiceException() {
    }

    public ServiceException(ResponseCodeEnum responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.setMessage(message);
    }

    @Override
    public String getMessage() {
        if (StringUtils.isEmpty(message)) {
            return super.getMessage();
        }
        return message;
    }

}
