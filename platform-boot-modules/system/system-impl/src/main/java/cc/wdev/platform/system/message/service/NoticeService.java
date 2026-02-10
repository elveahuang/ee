package cc.wdev.platform.system.message.service;

import cc.wdev.platform.commons.service.EntityService;
import cc.wdev.platform.system.message.domain.entity.NoticeEntity;
import cc.wdev.platform.system.message.domain.request.NoticeSearchRequest;
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
