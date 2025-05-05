package cc.elvea.platform.system.message.sender;

import cc.elvea.platform.commons.oapis.weixin.service.WeiXinMpService;
import cc.elvea.platform.commons.utils.ExceptionUtils;
import cc.elvea.platform.commons.utils.GsonUtils;
import cc.elvea.platform.system.message.model.dto.SendMessageDto;
import cc.elvea.platform.system.message.service.MessageContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
public class MessageWeChatSender implements MessageSender {

    private WeiXinMpService weiXinMpService;

    private MessageContentService messageContentService;

    @Override
    public void send(SendMessageDto message) {
        try {
            log.info("Send wechat message. message id [{}]. message content id [{}]. start", message.getId(), message.getContentId());

            // 检查企微服务是否已经启动
            if (this.weiXinMpService == null) {
                log.info("Send wechat message. message id [{}]. message content id [{}]. failed. wework is disabled. ", message.getId(), message.getContentId());
                return;
            }
            int code = 0;
            log.info("Send wechat message. message id [{}]. message content id [{}]. result - [{}].", message.getId(), message.getContentId(), GsonUtils.toJson(code));
            if (code == 0) {
                // 设置消息内容发送状态
                this.messageContentService.success(message.getContentId(), GsonUtils.toJson(code));
                log.info("Send wechat message. message id [{}]. message content id [{}]. done.", message.getId(), message.getContentId());
            } else {
                // 设置消息内容发送状态
                this.messageContentService.fail(message.getContentId(), GsonUtils.toJson(code));
                log.info("Send wechat message. message id [{}]. message content id [{}]. done.", message.getId(), message.getContentId());
            }
        } catch (Exception e) {
            // 设置消息内容发送状态
            this.messageContentService.fail(message.getContentId(), "", ExceptionUtils.getStackTraceAsString(e));
            log.error("Send wechat message. message id [{}]. message content id [{}]. failed.", message.getId(), message.getContentId(), e);
        }
    }

    @Autowired(required = false)
    public void setWeiXinMpService(WeiXinMpService weiXinMpService) {
        this.weiXinMpService = weiXinMpService;
    }

    @Autowired
    public void setMessageContentService(MessageContentService messageContentService) {
        this.messageContentService = messageContentService;
    }

}
