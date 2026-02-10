package cc.wdev.platform.system.core.domain.request;

import cc.wdev.platform.commons.web.request.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author elvea
 */
@Data
@SuperBuilder
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
