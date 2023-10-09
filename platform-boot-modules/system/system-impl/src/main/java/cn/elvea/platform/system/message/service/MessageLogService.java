package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.message.model.dto.SaveMessageLogDto;
import cn.elvea.platform.system.message.model.entity.MessageLogEntity;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MessageLogService extends EntityService<MessageLogEntity, Long> {

    void saveLog(SaveMessageLogDto messageLogDto);

}
