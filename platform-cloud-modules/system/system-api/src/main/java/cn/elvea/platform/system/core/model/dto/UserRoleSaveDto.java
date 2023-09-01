package cn.elvea.platform.system.core.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class UserRoleSaveDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @NotNull
    private Long userId;

    /**
     * 角色ID
     */
    private List<Long> roleIdList;

}
