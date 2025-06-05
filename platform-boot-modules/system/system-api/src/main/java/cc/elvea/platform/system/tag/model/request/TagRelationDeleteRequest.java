package cc.elvea.platform.system.tag.model.request;

import cc.elvea.platform.commons.web.request.Request;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagRelationDeleteRequest extends Request {
    /**
     * 标签类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagTypeId;
    /**
     * 标签ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;
}
