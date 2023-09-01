package cn.elvea.platform.commons.core.extensions.sms.tencent;

import cn.elvea.platform.commons.core.extensions.sms.SmsBody;
import cn.elvea.platform.commons.core.extensions.sms.SmsSender;
import cn.elvea.platform.commons.core.extensions.sms.SmsServer;
import com.aliyun.tea.TeaException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class TencentSmsSender implements SmsSender {

    private SmsServer server;

    @Override
    public void send(SmsBody body) throws Exception {
        this.send(this.server, body);
    }

    @Override
    public void send(SmsServer server, SmsBody body) throws Exception {
        try {
            SmsClient client = createClient(server.getTencent(), body);
            SendSmsRequest request = getRequest(server.getTencent(), body);
            SendSmsResponse response = client.SendSms(request);
            System.out.println(response);
        } catch (TeaException error) {
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }

    @NonNull
    private static SmsClient createClient(ServerConfig smsConfig, SmsBody body) throws Exception {
        Credential credential = new Credential(smsConfig.getSecretId(), smsConfig.getSecretKey());

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setReqMethod("POST");
        httpProfile.setConnTimeout(60);
        httpProfile.setEndpoint(smsConfig.getEndpoint());

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod("HmacSHA256");
        clientProfile.setHttpProfile(httpProfile);

        return new SmsClient(credential, smsConfig.getRegion(), clientProfile);
    }

    @NonNull
    private static SendSmsRequest getRequest(ServerConfig smsConfig, SmsBody body) {
        SendSmsRequest request = new SendSmsRequest();
        request.setSmsSdkAppId(smsConfig.getAppId());
        request.setSignName(smsConfig.getSignName());
        request.setTemplateId(smsConfig.getTemplate());
        request.setPhoneNumberSet(new String[]{body.getMobileNumber()});
        request.setTemplateParamSet(body.getParams().keySet().toArray(new String[0]));
        request.setSessionContext("");
        request.setExtendCode("");
        request.setSenderId("");
        return request;
    }

    /**
     * 腾讯云短信服务配置
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServerConfig implements Serializable {

        public final static String ENDPOINT = "sms.tencentcloudapi.com";

        @Builder.Default
        private Boolean enabled = Boolean.FALSE;

        @Builder.Default
        private String endpoint = ENDPOINT;

        private String region;

        private String appId;

        private String secretId;

        private String secretKey;

        private String signName;

        private String template;

    }

}
