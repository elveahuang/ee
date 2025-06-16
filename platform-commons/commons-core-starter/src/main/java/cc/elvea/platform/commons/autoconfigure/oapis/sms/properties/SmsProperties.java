package cc.elvea.platform.commons.autoconfigure.oapis.sms.properties;

import cc.elvea.platform.commons.oapis.sms.aliyun.AliyunSmsSender;
import cc.elvea.platform.commons.oapis.sms.enums.SmsTypeEnum;
import cc.elvea.platform.commons.oapis.sms.tencent.TencentSmsSender;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(SmsProperties.PREFIX)
public class SmsProperties {

    public static final String PREFIX = "platform.sms";

    private boolean enabled = false;

    private SmsTypeEnum type = SmsTypeEnum.Aliyun;

    @NestedConfigurationProperty
    private AliyunSmsSender.Config aliyun = AliyunSmsSender.Config.builder().build();

    @NestedConfigurationProperty
    private TencentSmsSender.TencentSmsConfig tencent = TencentSmsSender.TencentSmsConfig.builder().build();

}
