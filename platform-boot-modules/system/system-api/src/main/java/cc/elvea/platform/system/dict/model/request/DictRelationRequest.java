package cc.elvea.platform.system.dict.model.request;

import cc.elvea.platform.commons.web.request.Request;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
public class DictRelationRequest extends Request {
    /**
     * 目标实体ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long targetId;
    /**
     * 目标实体类型
     */
    private String targetType;
}
