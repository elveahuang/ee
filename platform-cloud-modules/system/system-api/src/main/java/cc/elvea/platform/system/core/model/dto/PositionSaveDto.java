package cc.elvea.platform.system.core.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Position
 *
 * @author elvea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class PositionSaveDto implements Serializable {
    private Long id;
    private String code;
    private String title;
    private String label;
    private String description;
    private Long parentId;
    private Boolean rootInd;
    private Boolean active;
}
