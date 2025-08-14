package top.baihu.platform.system.core.domain.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class AttachmentRelationRequest extends Request {

    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(title = "附件类型ID", defaultValue = "0")
    private Long typeId;

    @Schema(title = "附件类型")
    private String type;

}
