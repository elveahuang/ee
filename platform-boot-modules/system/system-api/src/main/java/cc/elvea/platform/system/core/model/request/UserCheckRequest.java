package cc.elvea.platform.system.core.model.request;

import cc.elvea.platform.commons.web.request.Request;
import lombok.*;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserCheckRequest extends Request {
    private Long id;
    private String username;
    private String email;
    private String mobileCountryCode;
    private String mobileNumber;
}
