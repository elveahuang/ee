package top.baihu.platform.commons.oapis.sms;

import lombok.Builder;
import lombok.Data;
import top.baihu.platform.commons.oapis.sms.aliyun.AliyunSmsSender;
import top.baihu.platform.commons.oapis.sms.enums.SmsTypeEnum;
import top.baihu.platform.commons.oapis.sms.tencent.TencentSmsSender;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
public class SmsConfig implements Serializable {

    @Builder.Default
    private SmsTypeEnum type = SmsTypeEnum.Aliyun;

    @Builder.Default
    private AliyunSmsSender.Config aliyun = AliyunSmsSender.Config.builder().build();

    @Builder.Default
    private TencentSmsSender.TencentSmsConfig tencent = TencentSmsSender.TencentSmsConfig.builder().build();

}
