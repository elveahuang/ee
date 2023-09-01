package cn.elvea.platform.commons.core.web;

import cn.elvea.platform.commons.core.enums.ResponseCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@Schema(description = "响应信息")
public class R<E> implements Serializable {

    @Schema(description = "响应编号")
    private String code;

    @Schema(description = "响应信息")
    private String message = "success";

    @Schema(description = "响应数据")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private E data;

    private R(String code) {
        this.code = code;
    }

    private R(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> R<T> success() {
        return new R<>(ResponseCodeEnum.SUCCESS.getCode());
    }

    public static <T> R<T> success(T data) {
        R<T> result = new R<>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 系统错误
     */
    public static <T> R<T> error() {
        return new R<>(ResponseCodeEnum.ERROR.getCode(), ResponseCodeEnum.ERROR.getMessage());
    }

    /**
     * 系统错误
     */
    public static <T> R<T> error(String message) {
        return new R<T>(ResponseCodeEnum.ERROR.getCode(), message);
    }

    /**
     * 系统错误
     */
    public static <T> R<T> error(String message, T data) {
        R<T> result = new R<>(ResponseCodeEnum.ERROR.getCode(), message);
        result.setData(data);
        return result;
    }

    /**
     * 业务处理失败
     */
    public static <T> R<T> fail(ResponseCodeEnum responseCodeEnum) {
        return new R<>(responseCodeEnum.getCode(), responseCodeEnum.getMessage());
    }

    /**
     * 业务处理失败
     */
    public static <T> R<T> fail(ResponseCodeEnum responseCodeEnum, String message) {
        return new R<>(responseCodeEnum.getCode(), message);
    }

    /**
     * 业务处理失败
     */
    public static <T> R<T> fail(ResponseCodeEnum responseCodeEnum, String message, T data) {
        R<T> response = new R<>(responseCodeEnum.getCode(), message);
        response.setData(data);
        return response;
    }

    /**
     * ¬
     * 业务处理失败
     */
    public static <T> R<T> fail(String message) {
        return new R<T>(ResponseCodeEnum.ERROR.getCode(), message);
    }

    /**
     * 判断是否成功
     */
    public boolean isSuccess() {
        return Objects.equals(this.code, ResponseCodeEnum.SUCCESS.getCode());
    }

}
