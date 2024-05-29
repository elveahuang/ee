package cc.elvea.platform.system.security.model.dto;

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
public class AuthorizationConsentDto implements Serializable {
    /**
     *
     */
    private String uuid;
    /**
     *
     */
    private String clientId;
    /**
     *
     */
    private String principalName;
    /**
     *
     */
    private String authorities;
}
