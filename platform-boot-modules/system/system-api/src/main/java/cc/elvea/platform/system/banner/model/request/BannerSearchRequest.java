package cc.elvea.platform.system.banner.model.request;

import cc.elvea.platform.commons.web.request.PageRequest;
import lombok.*;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BannerSearchRequest extends PageRequest {
    /**
     * 宣传栏类型关联字典CODE
     */
    private String code;
    /**
     * 宣传栏类型关联字典项CODE
     */
    private String[] itemCodes;
}
