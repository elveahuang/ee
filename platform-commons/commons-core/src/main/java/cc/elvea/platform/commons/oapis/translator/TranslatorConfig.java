package cc.elvea.platform.commons.oapis.translator;

import cc.elvea.platform.commons.oapis.translator.aliyun.AliyunTranslator;
import cc.elvea.platform.commons.oapis.translator.baidu.BaiduTranslator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslatorConfig {
    @Builder.Default
    private Boolean enabled = Boolean.FALSE;
    @Builder.Default
    private TranslatorType type = TranslatorType.Aliyun;
    @Builder.Default
    private BaiduTranslator.Config baidu = new BaiduTranslator.Config();
    @Builder.Default
    private AliyunTranslator.Config aliyun = new AliyunTranslator.Config();
}
