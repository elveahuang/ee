package top.baihu.platform.system.core.domain.request;

import lombok.*;
import top.baihu.platform.commons.web.request.Request;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountCheckRequest extends Request {
    private Long id;
    private String username;
    private String email;
    private String mobileCountryCode;
    private String mobileNumber;
}
