package cc.elvea.platform.commons.oapis.sms;

import cc.elvea.platform.commons.oapis.sms.aliyun.AliyunSmsSender;
import cc.elvea.platform.commons.oapis.sms.tencent.TencentSmsSender;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
public class SmsServer implements Serializable {

    @Builder.Default
    private AliyunSmsSender.ServerConfig aliyun = AliyunSmsSender.ServerConfig.builder().build();

    @Builder.Default
    private TencentSmsSender.ServerConfig tencent = TencentSmsSender.ServerConfig.builder().build();

}
