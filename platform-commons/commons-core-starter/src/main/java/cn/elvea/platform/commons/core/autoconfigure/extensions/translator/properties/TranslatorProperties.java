package cn.elvea.platform.commons.core.autoconfigure.extensions.translator.properties;

import cn.elvea.platform.commons.core.extensions.translator.TranslatorType;
import cn.elvea.platform.commons.core.extensions.translator.aliyun.AliyunTranslator;
import cn.elvea.platform.commons.core.extensions.translator.baidu.BaiduTranslator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = TranslatorProperties.PREFIX)
public class TranslatorProperties implements Serializable {

    public static final String PREFIX = "platform.translator";

    private Boolean enabled = Boolean.FALSE;

    private TranslatorType type = TranslatorType.Aliyun;

    @NestedConfigurationProperty
    private BaiduTranslator.Config baidu = new BaiduTranslator.Config();

    @NestedConfigurationProperty
    private AliyunTranslator.Config aliyun = new AliyunTranslator.Config();

}
