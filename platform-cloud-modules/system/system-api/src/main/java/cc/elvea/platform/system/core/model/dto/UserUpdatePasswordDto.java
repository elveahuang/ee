package cc.elvea.platform.system.core.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class UserUpdatePasswordDto implements Serializable {
    private Long id;
    private String oldPassword;
    private String confirmPassword;
    private String password;
}
