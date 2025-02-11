package cc.elvea.platform.commons.oapis.sms.aliyun;

import cc.elvea.platform.commons.oapis.sms.SmsSender;
import cc.elvea.platform.commons.oapis.sms.model.SmsBody;
import cc.elvea.platform.commons.utils.GsonUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class AliyunSmsSender implements SmsSender<AliyunSmsSender.Config> {

    public final static String ENDPOINT = "dysmsapi.aliyuncs.com";

    private Config config;

    @Override
    public void send(SmsBody body) throws Exception {
        this.send(this.config, body);
    }

    @Override
    public void send(Config config, SmsBody body) throws Exception {
        Client client = createClient(config, body);
        SendSmsRequest request = getRequest(config, body);
        try {
            SendSmsResponse response = client.sendSmsWithOptions(request, new RuntimeOptions());
            System.out.println(response);
        } catch (TeaException error) {
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }

    @NonNull
    private static Client createClient(Config config, SmsBody body) throws Exception {
        com.aliyun.teaopenapi.models.Config aliyunConfig = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId(config.getAccessKeyId())
                .setAccessKeySecret(config.getAccessKeySecret());
        aliyunConfig.setEndpoint(config.getEndpoint());
        return new Client(aliyunConfig);
    }

    @NonNull
    private static SendSmsRequest getRequest(Config config, SmsBody body) {
        String template = StringUtils.isNotEmpty(body.getTemplate()) ? body.getTemplate() : config.getTemplate();

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(body.getMobileNumber());
        request.setSignName(config.getSignName());
        request.setTemplateCode(template);
        request.setTemplateParam(GsonUtils.toJson(body.getParams()));

        return request;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Config implements Serializable {
        @Builder.Default
        private boolean enabled = false;
        @Builder.Default
        private String endpoint = ENDPOINT;
        private String accessKeyId;
        private String accessKeySecret;
        private String signName;
        private String template;
    }

}
