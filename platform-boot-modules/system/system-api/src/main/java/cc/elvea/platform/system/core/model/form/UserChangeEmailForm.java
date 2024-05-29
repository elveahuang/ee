package cc.elvea.platform.system.core.model.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserChangeEmailForm implements Serializable {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String newEmail;
    @NotEmpty
    private String captchaKey;
    @NotEmpty
    private String captchaValue;
}
