package cc.wdev.platform.system.core.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagVo implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 标签类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;
    /**
     * 标题
     */
    private String title;
    /**
     * 序号
     */
    private Integer idx;
}
