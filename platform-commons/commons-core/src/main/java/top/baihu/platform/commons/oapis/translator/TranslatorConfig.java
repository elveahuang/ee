package top.baihu.platform.commons.oapis.translator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.baihu.platform.commons.oapis.translator.aliyun.AliyunTranslator;
import top.baihu.platform.commons.oapis.translator.enums.TranslatorTypeEnum;
import top.baihu.platform.commons.oapis.translator.tencent.TencentTranslator;

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
