package cc.elvea.platform.system.core.model.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePasswordForm {
    @NotEmpty
    private String originalPassword;
    @NotEmpty
    private String newPassword;
}
