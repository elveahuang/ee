package cc.elvea.platform.system.config.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class ConfigDto implements Serializable {
    private String configGroup;
    private String configKey;
    private String configValue;
}
