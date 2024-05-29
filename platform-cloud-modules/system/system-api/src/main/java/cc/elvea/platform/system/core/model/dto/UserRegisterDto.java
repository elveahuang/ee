package cc.elvea.platform.system.core.model.dto;

import jakarta.validation.constraints.NotEmpty;
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
public class UserRegisterDto implements Serializable {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
