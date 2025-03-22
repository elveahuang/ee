package cc.elvea.platform.system.message.service;

import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.message.model.entity.NoticeEntity;
import cc.elvea.platform.system.message.request.NoticeSearchRequest;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 */
public interface NoticeService extends EntityService<NoticeEntity, Long> {

    /**
     * 获取当前登录用户的系统通知列表
     */
    Page<NoticeEntity> findMyNoticeByUserId(NoticeSearchRequest request);

    /**
     * 获取指定用户所有的消息通知
     */
    Page<NoticeEntity> findByUserId(NoticeSearchRequest request);

}
