package cc.elvea.platform.system.core.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class EntityRelationSaveDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 实体ID
     */
    @NotNull
    private Long entityId;

    /**
     * 祖先ID
     */
    private List<Long> ancestorIdList;

    /**
     * 关联类型
     */
    @NotNull
    private String relationType;

}
