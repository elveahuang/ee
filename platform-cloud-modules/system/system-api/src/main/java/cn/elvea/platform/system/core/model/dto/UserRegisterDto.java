package cn.elvea.platform.system.core.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class UserRegisterDto implements Serializable {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
