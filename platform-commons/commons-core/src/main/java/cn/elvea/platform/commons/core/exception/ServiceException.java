package cn.elvea.platform.commons.core.exception;

import com.aliyun.tea.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Map;

/**
 * 服务异常
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
@Setter
public class ServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public String code;

    public String message;

    public Map<String, Object> data;

    public Integer statusCode;

    public String description;

    public ServiceException() {
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.setMessage(message);
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        if (StringUtils.isEmpty(message)) {
            return super.getMessage();
        }
        return message;
    }

}
