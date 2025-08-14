package top.baihu.platform.commons.autoconfigure.oapis.sms.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import top.baihu.platform.commons.oapis.sms.aliyun.AliyunSmsSender;
import top.baihu.platform.commons.oapis.sms.enums.SmsTypeEnum;
import top.baihu.platform.commons.oapis.sms.tencent.TencentSmsSender;

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
