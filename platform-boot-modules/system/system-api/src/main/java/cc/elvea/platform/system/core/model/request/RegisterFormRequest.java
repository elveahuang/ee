package cc.elvea.platform.system.core.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
public class RegisterFormRequest implements Serializable {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
