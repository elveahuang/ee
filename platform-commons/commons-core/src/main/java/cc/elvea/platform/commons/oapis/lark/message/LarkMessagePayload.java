package cc.elvea.platform.commons.oapis.lark.message;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
public class LarkMessagePayload implements Serializable {
    private String type;
    @JsonRawValue
    private Object content;
}
