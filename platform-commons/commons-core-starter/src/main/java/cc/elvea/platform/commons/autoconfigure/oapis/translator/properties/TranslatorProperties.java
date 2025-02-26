package cc.elvea.platform.commons.autoconfigure.oapis.translator.properties;

import cc.elvea.platform.commons.oapis.translator.aliyun.AliyunTranslator;
import cc.elvea.platform.commons.oapis.translator.enums.TranslatorTypeEnum;
import cc.elvea.platform.commons.oapis.translator.tencent.TencentTranslator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = TranslatorProperties.PREFIX)
public class TranslatorProperties implements Serializable {

    public static final String PREFIX = "platform.translator";

    private boolean enabled = false;

    private TranslatorTypeEnum type = TranslatorTypeEnum.Aliyun;

    @NestedConfigurationProperty
    private TencentTranslator.Config tencent = new TencentTranslator.Config();

    @NestedConfigurationProperty
    private AliyunTranslator.Config aliyun = new AliyunTranslator.Config();

}
