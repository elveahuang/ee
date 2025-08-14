package top.baihu.platform.system.message.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseEntityService;
import top.baihu.platform.system.message.domain.entity.MessageHistoryEntity;
import top.baihu.platform.system.message.repository.MessageHistoryRepository;
import top.baihu.platform.system.message.service.MessageHistoryService;

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
