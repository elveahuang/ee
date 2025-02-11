package cc.elvea.platform.system.tag.model.request;

import cc.elvea.platform.commons.web.request.Request;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author elvea
 */
@Data
@Builder
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
