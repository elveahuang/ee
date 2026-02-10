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
public class DictRelationVo implements Serializable {
    /**
     * 目标实体类型
     */
    private String targetType;
    /**
     * 目标实体ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long targetId;
    /**
     * 字典ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dictId;
    /**
     * 字典项ID
     */
    @Builder.Default
    private List<Long> ids = emptyList();
    /**
     * 字典项
     */
    @Builder.Default
    private List<DictItemVo> items = emptyList();
}
