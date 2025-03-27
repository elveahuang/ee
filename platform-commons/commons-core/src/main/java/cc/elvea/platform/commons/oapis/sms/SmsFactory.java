package cc.elvea.platform.commons.oapis.sms;

import cc.elvea.platform.commons.oapis.sms.aliyun.AliyunSmsSender;
import cc.elvea.platform.commons.oapis.sms.enums.SmsTypeEnum;
import cc.elvea.platform.commons.oapis.sms.tencent.TencentSmsSender;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public record SmsFactory(SmsConfig config) {

    public SmsSender<?> getSmsSender() {
        if (SmsTypeEnum.Tencent.equals(this.config.getType())) {
            return getTencentSmsSender();
        }
        return getAliyunSmsSender();
    }

    public AliyunSmsSender getAliyunSmsSender() {
        return this.getAliyunSmsSender(this.config.getAliyun());
    }

    public AliyunSmsSender getAliyunSmsSender(AliyunSmsSender.Config config) {
        return new AliyunSmsSender(config);
    }

    public TencentSmsSender getTencentSmsSender() {
        return this.getTencentSmsSender(this.config.getTencent());
    }

    public TencentSmsSender getTencentSmsSender(TencentSmsSender.TencentSmsConfig config) {
        return new TencentSmsSender(config);
    }

}
