package cc.elvea.platform.commons.autoconfigure.oapis.sms.properties;

import cc.elvea.platform.commons.oapis.sms.SmsServerType;
import cc.elvea.platform.commons.oapis.sms.aliyun.AliyunSmsSender;
import cc.elvea.platform.commons.oapis.sms.tencent.TencentSmsSender;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(SmsProperties.PREFIX)
public class SmsProperties {

    public static final String PREFIX = "platform.sms";

    private boolean enabled = false;

    private SmsServerType type = SmsServerType.Aliyun;

    @NestedConfigurationProperty
    private AliyunSmsSender.ServerConfig aliyun = AliyunSmsSender.ServerConfig.builder().build();

    @NestedConfigurationProperty
    private TencentSmsSender.ServerConfig tencent = TencentSmsSender.ServerConfig.builder().build();

}
