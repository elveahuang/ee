package cn.elvea.platform.system.message.model.dto;

import cn.elvea.platform.system.message.enums.MessageTemplateTypeEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
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
public class CreateMessageDto implements Serializable {

    private String type;

    private Long TypeId;

    private String subject;

    private String content;

    private String template;

    /**
     * 发件人
     */
    private MessageUserDto sender;

    /**
     * 收件人
     */
    @Builder.Default
    private List<MessageUserDto> recipients = Lists.newArrayList();

    /**
     * 目标发送时间
     */
    private LocalDateTime targetSentDatetime;

    /**
     * 参数
     */
    @Builder.Default
    private Map<String, Object> params = Maps.newHashMap();

    /**
     * 指定模版
     */
    @Builder.Default
    private List<MessageTemplateTypeEnum> templateTypeList = Lists.newArrayList();

}
