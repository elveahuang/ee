package cc.elvea.platform.system.message.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class SaveMessageLogDto implements Serializable {
    private Long messageId;
    private String details;
    private String exception;
}
