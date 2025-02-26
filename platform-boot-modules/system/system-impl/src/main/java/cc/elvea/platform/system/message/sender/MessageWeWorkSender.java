package cc.elvea.platform.system.message.sender;

import cc.elvea.platform.commons.oapis.weixin.service.WeiXinCpService;
import cc.elvea.platform.commons.utils.ExceptionUtils;
import cc.elvea.platform.commons.utils.GsonUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.message.model.dto.SendMessageDto;
import cc.elvea.platform.system.message.service.MessageContentService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.bean.message.WxCpMessageSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
public class MessageWeWorkSender implements MessageSender {

    private WeiXinCpService weiXinCpService;

    private MessageContentService messageContentService;

    @Override
    public void send(SendMessageDto message) {
        try {
            log.info("Send wework message. message id [{}]. message content id [{}]. start", message.getId(), message.getContentId());

            // 检查企微服务是否已经启动
            if (this.weiXinCpService == null) {
                log.info("Send wework message. message id [{}]. message content id [{}]. failed. wework is disabled. ", message.getId(), message.getContentId());
                return;
            }

            String username = message.getRecipient().getUsername();
            String mobile = message.getRecipient().getMobileNumber();

            String wxCpUserId = "";
            if (StringUtils.isNotEmpty(username)) {
                try {
                    // 先检测当前用户在企业微信里面是否存在
                    log.info("Send wework message. message id [{}]. message content id [{}]. check user [{}].", message.getId(), message.getContentId(), username);

                    WxCpUser wxCpUser = weiXinCpService.getUserService().getById(username);
                    wxCpUserId = wxCpUser.getUserId();

                    log.info("Send wework message. message id [{}]. message content id [{}]. check user [{}]. valid user [{}].", message.getId(), message.getContentId(), username, wxCpUserId);
                } catch (Exception e) {
                    log.info("Send wework message. message id [{}]. message content id [{}]. check user [{}]. failed.", message.getId(), message.getContentId(), username);
                }
            }

            if (StringUtils.isEmpty(wxCpUserId) && StringUtils.isNotEmpty(mobile)) {
                try {
                    // 先检测当前手机号码在企业微信里面是否存在
                    log.info("Send wework message. message id [{}]. message content id [{}]. check mobile [{}].", message.getId(), message.getContentId(), mobile);

                    wxCpUserId = weiXinCpService.getUserService().getUserId(mobile);
                    log.info("Send wework message. message id [{}]. message content id [{}]. check mobile [{}]. valid user [{}].", message.getId(), message.getContentId(), mobile, wxCpUserId);
                } catch (Exception e) {
                    log.info("Send wework message. message id [{}]. message content id [{}]. check mobile [{}]. failed.", message.getId(), message.getContentId(), mobile);
                }
            }

            if (StringUtils.isEmpty(wxCpUserId)) {
                log.info("Send wework message. message id [{}]. message content id [{}]. invalid user.", message.getId(), message.getContentId());
                return;
            }

            // 发送企微消息
            WxCpMessage wxCpMessage = WxCpMessage
                    .TEXT()
                    .agentId(weiXinCpService.getConfigStorage().getAgentId())
                    .toUser(wxCpUserId)
                    .content(message.getContent())
                    .build();
            WxCpMessageSendResult result = weiXinCpService.getMessageService().send(wxCpMessage);

            log.info("Send wework message. message id [{}]. message content id [{}]. result - [{}].", message.getId(), message.getContentId(), GsonUtils.toJson(result));
            if (result.getErrCode() == 0) {
                // 设置消息内容发送状态
                this.messageContentService.success(message.getContentId(), GsonUtils.toJson(result));
                log.info("Send wework message. message id [{}]. message content id [{}]. done.", message.getId(), message.getContentId());
            } else {
                // 设置消息内容发送状态
                this.messageContentService.fail(message.getContentId(), GsonUtils.toJson(result));
                log.info("Send wework message. message id [{}]. message content id [{}]. done.", message.getId(), message.getContentId());
            }
        } catch (Exception e) {
            // 设置消息内容发送状态
            this.messageContentService.fail(message.getContentId(), "", ExceptionUtils.getStackTraceAsString(e));
            log.error("Send wework message. message id [{}]. message content id [{}]. failed.", message.getId(), message.getContentId(), e);
        }
    }

    @Autowired(required = false)
    public void setWeiXinCpService(WeiXinCpService weiXinCpService) {
        this.weiXinCpService = weiXinCpService;
    }

    @Autowired
    public void setMessageContentService(MessageContentService messageContentService) {
        this.messageContentService = messageContentService;
    }

}
