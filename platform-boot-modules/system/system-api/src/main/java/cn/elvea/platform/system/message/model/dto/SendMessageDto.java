package cn.elvea.platform.system.message.model.dto;

import com.google.common.collect.Maps;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class SendMessageDto implements Serializable {

    private Long id;

    private Long contentId;

    private String subject;

    private String content;

    @Builder.Default
    private Map<String, Object> params = Maps.newHashMap();

    private MessageSenderDto sender;

    private MessageRecipientDto recipient;

}
