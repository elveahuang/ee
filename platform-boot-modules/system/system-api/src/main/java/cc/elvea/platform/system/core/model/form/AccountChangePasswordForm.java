package cc.elvea.platform.system.core.model.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountChangePasswordForm {
    @NotEmpty
    private String originalPassword;
    @NotEmpty
    private String newPassword;
}
