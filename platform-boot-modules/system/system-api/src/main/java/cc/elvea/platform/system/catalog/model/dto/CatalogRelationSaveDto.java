package cc.elvea.platform.system.catalog.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

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
public class CatalogRelationSaveDto implements Serializable {

    /**
     * 实体ID
     */
    @NotNull
    private Long entityId;

    /**
     * 祖先ID
     */
    @NotNull
    private List<Long> ancestorIdList;

    /**
     * 关联类型
     */
    @NotNull
    private String relationType;

    /**
     * 上级关联类型
     */
    @NotNull
    private String ancestorRelationType;

}
