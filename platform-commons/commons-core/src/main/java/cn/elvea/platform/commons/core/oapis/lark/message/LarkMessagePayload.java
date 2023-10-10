package cn.elvea.platform.commons.core.oapis.lark.message;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class LarkMessagePayload implements Serializable {
    private String type;
    @JsonRawValue
    private Object content;
}
