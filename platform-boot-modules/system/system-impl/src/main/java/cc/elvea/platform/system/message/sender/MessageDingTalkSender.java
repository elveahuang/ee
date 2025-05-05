package cc.elvea.platform.system.message.sender;

import cc.elvea.platform.commons.oapis.dingtalk.service.DingTalkService;
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
public class MessageDingTalkSender implements MessageSender {

    private DingTalkService dingTalkService;

    private MessageContentService messageContentService;

    @Override
    public void send(SendMessageDto message) {
        try {
            log.info("Send dingtalk message. message id [{}]. message content id [{}]. start", message.getId(), message.getContentId());

            // 检查钉钉服务是否已经启动
            if (this.dingTalkService == null) {
                log.error("Send dingtalk message [{}] failed. lark is disabled.", message.getId());
                return;
            }

            int code = 0;

            log.info("Send dingtalk message. message id [{}]. message content id [{}]. result - [{}].", message.getId(), message.getContentId(), GsonUtils.toJson(code));
            if (code == 0) {
                // 设置消息内容发送状态
                this.messageContentService.success(message.getContentId(), GsonUtils.toJson(code));
                log.info("Send dingtalk message. message id [{}]. message content id [{}]. done.", message.getId(), message.getContentId());
            } else {
                // 设置消息内容发送状态
                this.messageContentService.fail(message.getContentId(), GsonUtils.toJson(code));
                log.info("Send dingtalk message. message id [{}]. message content id [{}]. done.", message.getId(), message.getContentId());
            }
        } catch (Exception e) {
            // 设置消息内容发送状态
            this.messageContentService.fail(message.getContentId(), "", ExceptionUtils.getStackTraceAsString(e));
            log.error("Send lark message. message id [{}]. message content id [{}]. failed.", message.getId(), message.getContentId(), e);
        }
    }

    @Autowired(required = false)
    public void setDingTalkService(DingTalkService dingTalkService) {
        this.dingTalkService = dingTalkService;
    }

    @Autowired
    public void setMessageContentService(MessageContentService messageContentService) {
        this.messageContentService = messageContentService;
    }

}
