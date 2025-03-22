package cc.elvea.platform.system.attachment.model.request;

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
public class AttachmentTypeRequest extends Request {

    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(title = "附件类型ID", defaultValue = "0")
    private Long typeId;

    @Schema(title = "附件类型")
    private String type;

}
