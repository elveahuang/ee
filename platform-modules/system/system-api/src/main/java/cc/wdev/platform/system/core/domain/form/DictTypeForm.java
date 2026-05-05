package cc.wdev.platform.system.core.domain.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DictTypeForm implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 编号
     */
    private String code;
    /**
     * 标题
     */
    @NotEmpty
    private String title;
    /**
     * 标签
     */
    @NotEmpty
    private String label;
    /**
     * 描述
     */
    private String description;
    /**
     * 来源
     */
    private Integer source;
}
