package cn.elvea.platform.commons.core.autoconfigure.extensions.sms.properties;

import cn.elvea.platform.commons.core.extensions.sms.aliyun.AliyunSmsSender;
import cn.elvea.platform.commons.core.extensions.sms.tencent.TencentSmsSender;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(SmsProperties.PREFIX)
public class SmsProperties {

    public static final String PREFIX = "platform.sms";

    private Boolean enabled = Boolean.FALSE;

    @NestedConfigurationProperty
    private AliyunSmsSender.ServerConfig aliyun = AliyunSmsSender.ServerConfig.builder().build();

    @NestedConfigurationProperty
    private TencentSmsSender.ServerConfig tencent = TencentSmsSender.ServerConfig.builder().build();

}
