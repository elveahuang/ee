package cn.elvea.platform.system.message.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseEntityService;
import cn.elvea.platform.system.message.model.dto.SaveMessageLogDto;
import cn.elvea.platform.system.message.model.entity.MessageLogEntity;
import cn.elvea.platform.system.message.repository.MessageLogRepository;
import cn.elvea.platform.system.message.service.MessageLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
@Service
public class MessageLogServiceImpl
        extends BaseEntityService<MessageLogEntity, Long, MessageLogRepository>
        implements MessageLogService {

    @Override
    public void saveLog(SaveMessageLogDto messageLogDto) {
        MessageLogEntity entity = MessageLogEntity.builder()
                .messageId(messageLogDto.getMessageId())
                .details(messageLogDto.getDetails())
                .exception(messageLogDto.getException())
                .build();
        this.save(entity);
    }

}
