package cn.elvea.platform.system.message.sender;

import cn.elvea.platform.commons.core.oapis.weixin.service.WeiXinCpService;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.elvea.platform.system.message.model.dto.SendMessageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class MessageWeWorkSender implements MessageSender {

    private final WeiXinCpService cpService;

    @Override
    public void send(SendMessageDto message) {
        Long messageId = message.getId();
        try {
            log.info("Send wework message [{}] start", messageId);

            String username = message.getRecipient().getUsername();
            String mobile = message.getRecipient().getMobileNumber();

            String wxCpUserId = "";
            if (StringUtils.isNotEmpty(username)) {
                try {
                    // 先检测当前用户在企业微信里面是否存在
                    log.info("Send wework message [{}]. check user [{}].", messageId, username);
                    WxCpUser wxCpUser = cpService.getUserService().getById(username);
                    wxCpUserId = wxCpUser.getUserId();
                    log.info("Send wework message [{}]. valid user. username [{}]. wxCpUserId [{}].", messageId, username, wxCpUserId);
                } catch (Exception e) {
                    log.error("Send wework message [{}]. invalid user. username [{}]. ", messageId, username, e);
                }
            }

            if (StringUtils.isEmpty(wxCpUserId) && StringUtils.isNotEmpty(mobile)) {
                try {
                    // 先检测当前用户在企业微信里面是否存在
                    log.info("Send wework message [{}]. check user [{}].", messageId, mobile);
                    wxCpUserId = cpService.getUserService().getUserId(mobile);
                    log.info("Send wework message [{}]. valid user. mobile [{}]. wxCpUserId [{}].", messageId, mobile, wxCpUserId);
                } catch (Exception e) {
                    log.error("Send wework message [{}]. invalid user. mobile [{}]. ", messageId, mobile, e);
                }
            }

            if (StringUtils.isEmpty(wxCpUserId)) {
                log.info("Send wework message [{}] failed. invalid user.", message.getId());
                return;
            }

            WxCpMessage wxCpMessage = WxCpMessage
                    .TEXT()
                    .agentId(cpService.getConfigStorage().getAgentId())
                    .toUser(wxCpUserId)
                    .content(message.getContent())
                    .build();
            cpService.getMessageService().send(wxCpMessage);
            log.info("Send wework message [{}] done.", message.getId());
        } catch (Exception e) {
            log.error("Send wework message [{}] failed.", message.getId(), e);
        }
    }

}
