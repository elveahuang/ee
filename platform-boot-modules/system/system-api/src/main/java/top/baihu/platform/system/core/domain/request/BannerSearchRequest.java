package top.baihu.platform.system.core.domain.request;

import lombok.*;
import top.baihu.platform.commons.web.request.PageRequest;

/**
 * @author elvea
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
