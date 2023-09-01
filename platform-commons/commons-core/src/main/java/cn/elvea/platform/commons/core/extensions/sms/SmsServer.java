package cn.elvea.platform.commons.core.extensions.sms;

import cn.elvea.platform.commons.core.extensions.sms.aliyun.AliyunSmsSender;
import cn.elvea.platform.commons.core.extensions.sms.tencent.TencentSmsSender;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
public class SmsServer implements Serializable {

    @Builder.Default
    private AliyunSmsSender.ServerConfig aliyun = AliyunSmsSender.ServerConfig.builder().build();

    @Builder.Default
    private TencentSmsSender.ServerConfig tencent = TencentSmsSender.ServerConfig.builder().build();

}
