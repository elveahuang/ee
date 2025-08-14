package top.baihu.platform.system.message.service;

import org.springframework.data.domain.Page;
import top.baihu.platform.commons.service.EntityService;
import top.baihu.platform.system.message.domain.entity.NoticeEntity;
import top.baihu.platform.system.message.domain.request.NoticeSearchRequest;

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
