package cc.elvea.platform.system.core.model.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserForm implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;
    @NotEmpty(message = "{system__validation__user__username_not_empty}")
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
}
