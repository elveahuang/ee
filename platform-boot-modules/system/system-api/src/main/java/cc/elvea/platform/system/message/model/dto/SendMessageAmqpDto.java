package cc.elvea.platform.system.message.model.dto;

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
public class SendMessageAmqpDto implements Serializable {

    private Long id;

}
