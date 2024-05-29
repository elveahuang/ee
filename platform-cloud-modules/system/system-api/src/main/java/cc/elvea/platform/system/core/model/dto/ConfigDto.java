package cc.elvea.platform.system.core.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统设置
 *
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
    /**
     * 参数名
     */
    private String code;
    /**
     * 参数值
     */
    private String value;
}
