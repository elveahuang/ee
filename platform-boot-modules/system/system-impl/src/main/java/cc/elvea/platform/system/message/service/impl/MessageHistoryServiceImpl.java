package cc.elvea.platform.system.message.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseEntityService;
import cc.elvea.platform.system.message.model.entity.MessageHistoryEntity;
import cc.elvea.platform.system.message.repository.MessageHistoryRepository;
import cc.elvea.platform.system.message.service.MessageHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
@Service
public class MessageHistoryServiceImpl
        extends BaseEntityService<MessageHistoryEntity, Long, MessageHistoryRepository>
        implements MessageHistoryService {
}
