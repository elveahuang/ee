package cc.wdev.platform.system.core.domain.request;

import cc.wdev.platform.commons.web.request.Request;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
public class DictTypeRequest extends Request {

    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(title = "字典类型ID", defaultValue = "0")
    private Long typeId;

    @Schema(title = "字典类型")
    private String type;

    @Schema(title = "是否包含字典项")
    @Builder.Default
    private boolean withItem = false;

}
