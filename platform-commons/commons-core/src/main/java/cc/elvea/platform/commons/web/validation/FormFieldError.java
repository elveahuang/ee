package cc.elvea.platform.commons.web.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 表单属性错误信息
 *
 * @author elvea
 * @since 24.1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormFieldError implements Serializable {
    private String property;
    private String message;
}
