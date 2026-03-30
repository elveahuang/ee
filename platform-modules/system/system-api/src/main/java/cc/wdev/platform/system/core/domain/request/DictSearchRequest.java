package cc.wdev.platform.system.core.domain.request;

import cc.wdev.platform.commons.web.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author elvea
 */
@Data
@SuperBuilder
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
