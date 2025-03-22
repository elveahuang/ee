package cc.elvea.platform.commons.oapis.sms.tencent;

import cc.elvea.platform.commons.oapis.sms.SmsSender;
import cc.elvea.platform.commons.oapis.sms.model.SmsBody;
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
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class TencentSmsSender implements SmsSender<TencentSmsSender.TencentSmsConfig> {

    public final static String ENDPOINT = "sms.tencentcloudapi.com";

    private TencentSmsConfig config;

    @Override
    public void send(SmsBody body) throws Exception {
        this.send(this.config, body);
    }

    @Override
    public void send(TencentSmsConfig config, SmsBody body) throws Exception {
        try {
            SmsClient client = createClient(config, body);
            SendSmsRequest request = getRequest(config, body);
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
    private static SmsClient createClient(TencentSmsConfig config, SmsBody body) throws Exception {
        Credential credential = new Credential(config.getSecretId(), config.getSecretKey());

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setReqMethod("POST");
        httpProfile.setConnTimeout(60);
        httpProfile.setEndpoint(config.getEndpoint());

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod("HmacSHA256");
        clientProfile.setHttpProfile(httpProfile);

        return new SmsClient(credential, config.getRegion(), clientProfile);
    }

    @NonNull
    private static SendSmsRequest getRequest(TencentSmsConfig config, SmsBody body) {
        SendSmsRequest request = new SendSmsRequest();
        request.setSmsSdkAppId(config.getAppId());
        request.setSignName(config.getSignName());
        request.setTemplateId(config.getTemplate());
        request.setPhoneNumberSet(new String[]{body.getMobileNumber()});
        request.setTemplateParamSet(body.getParams().keySet().toArray(new String[0]));
        request.setSessionContext("");
        request.setExtendCode("");
        request.setSenderId("");
        return request;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TencentSmsConfig implements Serializable {
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
