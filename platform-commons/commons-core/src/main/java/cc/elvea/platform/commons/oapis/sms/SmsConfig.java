package cc.elvea.platform.commons.oapis.sms;

import cc.elvea.platform.commons.oapis.sms.aliyun.AliyunSmsSender;
import cc.elvea.platform.commons.oapis.sms.enums.SmsTypeEnum;
import cc.elvea.platform.commons.oapis.sms.tencent.TencentSmsSender;
import lombok.Builder;
import lombok.Data;

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
