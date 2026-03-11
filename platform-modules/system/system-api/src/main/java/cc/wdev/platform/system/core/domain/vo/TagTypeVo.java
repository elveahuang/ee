package cc.wdev.platform.system.core.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TagTypeVo implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 标签类型编号
     */
    private String code;
    /**
     * 标签类型标题
     */
    private String title;
    /**
     * 明细
     */
    @Builder.Default
    private List<TagVo> items = emptyList();
}
