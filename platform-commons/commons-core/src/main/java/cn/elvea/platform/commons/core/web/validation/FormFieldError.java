package cn.elvea.platform.commons.core.web.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 表单属性错误信息
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormFieldError implements Serializable {
    private String property;
    private String message;
}
