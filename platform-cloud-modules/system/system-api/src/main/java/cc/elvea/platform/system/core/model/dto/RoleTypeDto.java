package cc.elvea.platform.system.core.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * RoleTypeDto
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
public class RoleTypeDto implements Serializable {
    /**
     * ID
     */
    private Long id;
    /**
     * 编号
     */
    private String code;
    /**
     * 文本
     */
    private String label;
    /**
     * 备注
     */
    private String description;
}
