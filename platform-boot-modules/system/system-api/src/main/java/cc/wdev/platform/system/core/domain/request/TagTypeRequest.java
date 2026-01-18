package cc.wdev.platform.system.core.domain.request;

import cc.wdev.platform.commons.web.request.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagTypeRequest extends Request {

    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(title = "标签类型ID", defaultValue = "0")
    private Long typeId;

    @Schema(title = "标签类型")
    private String type;

    @Schema(title = "是否包含标签")
    @Builder.Default
    private boolean withItem = false;

}
