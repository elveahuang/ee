package cc.wdev.platform.system.core.domain.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class RoleDto implements Serializable {
    /**
     * ID
     */
    private Long id;
    /**
     * 角色编号
     */
    private String code;
    /**
     * 角色编号
     */
    private String label;
    /**
     * 角色标题
     */
    private String title;
}
