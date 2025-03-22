package cc.elvea.platform.commons.oapis.translator;

import cc.elvea.platform.commons.oapis.translator.aliyun.AliyunTranslator;
import cc.elvea.platform.commons.oapis.translator.enums.TranslatorTypeEnum;
import cc.elvea.platform.commons.oapis.translator.tencent.TencentTranslator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslatorConfig {

    @Builder.Default
    private Boolean enabled = Boolean.FALSE;

    @Builder.Default
    private TranslatorTypeEnum type = TranslatorTypeEnum.Aliyun;

    @Builder.Default
    private TencentTranslator.Config tencent = new TencentTranslator.Config();

    @Builder.Default
    private AliyunTranslator.Config aliyun = new AliyunTranslator.Config();

}
