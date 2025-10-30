package cc.wdev.platform.system.core.domain.request;

import cc.wdev.platform.commons.web.request.Request;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
public class DictRelationDeleteRequest extends Request {
    /**
     * 类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
}
