package top.baihu.platform.system.core.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class DictSearchRequest extends PageRequest {
    /**
     * 标签类型ID
     */
    @Builder.Default
    @Schema(title = "标签类型ID", defaultValue = "0")
    private Long typeId = 0L;
    /**
     * 标签类型
     */
    @Schema(title = "标签类型")
    private String type;
}
