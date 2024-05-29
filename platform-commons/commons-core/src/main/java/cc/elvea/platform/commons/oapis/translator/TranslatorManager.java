package cc.elvea.platform.commons.oapis.translator;

import cc.elvea.platform.commons.oapis.translator.aliyun.AliyunTranslator;
import cc.elvea.platform.commons.oapis.translator.baidu.BaiduTranslator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class TranslatorManager {

    private final TranslatorConfig config;

    public TranslatorManager(TranslatorConfig config) {
        this.config = config;
    }

    public Translator getTranslator() {
        if (TranslatorType.Aliyun.equals(config.getType())) {
            return getAliyunTranslator();
        } else if (TranslatorType.Baidu.equals(config.getType())) {
            return getBaiduTranslator();
        }
        return getAliyunTranslator();
    }

    public BaiduTranslator getBaiduTranslator() {
        return getBaiduTranslator(this.config.getBaidu());
    }

    public BaiduTranslator getBaiduTranslator(BaiduTranslator.Config config) {
        return new BaiduTranslator(config);
    }

    public AliyunTranslator getAliyunTranslator() {
        return this.getAliyunTranslator(this.config.getAliyun());
    }

    public AliyunTranslator getAliyunTranslator(AliyunTranslator.Config config) {
        return new AliyunTranslator(config);
    }

}
