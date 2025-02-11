package cc.elvea.platform.commons.oapis.translator;

import cc.elvea.platform.commons.oapis.translator.aliyun.AliyunTranslator;
import cc.elvea.platform.commons.oapis.translator.enums.TranslatorTypeEnum;
import cc.elvea.platform.commons.oapis.translator.tencent.TencentTranslator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public class TranslatorFactory {

    private final TranslatorConfig config;

    public TranslatorFactory(TranslatorConfig config) {
        this.config = config;
    }

    public Translator getTranslator() {
        if (TranslatorTypeEnum.Tencent.equals(config.getType())) {
            return getTencentTranslator();
        }
        return getAliyunTranslator();
    }

    public AliyunTranslator getAliyunTranslator() {
        return this.getAliyunTranslator(this.config.getAliyun());
    }

    public AliyunTranslator getAliyunTranslator(AliyunTranslator.Config config) {
        return new AliyunTranslator(config);
    }

    public TencentTranslator getTencentTranslator() {
        return getTencentTranslator(this.config.getTencent());
    }

    public TencentTranslator getTencentTranslator(TencentTranslator.Config config) {
        return new TencentTranslator(config);
    }

}
