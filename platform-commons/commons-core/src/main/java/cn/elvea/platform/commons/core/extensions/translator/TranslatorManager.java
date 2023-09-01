package cn.elvea.platform.commons.core.extensions.translator;

import cn.elvea.platform.commons.core.extensions.translator.aliyun.AliyunTranslator;
import cn.elvea.platform.commons.core.extensions.translator.baidu.BaiduTranslator;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class TranslatorManager {

    private final TranslatorType type;

    private final Map<TranslatorType, Translator> translatorMap = Maps.newHashMap();

    public TranslatorManager(TranslatorType type) {
        this.type = type;
    }

    public void addTranslator(TranslatorType type, Translator translator) {
        translatorMap.put(type, translator);
    }

    public Translator getTranslator() {
        return this.translatorMap.get(this.type);
    }

    public BaiduTranslator getBaiduTranslator() {
        return (BaiduTranslator) this.translatorMap.get(TranslatorType.Baidu);
    }

    public BaiduTranslator getBaiduTranslator(BaiduTranslator.Config config) {
        return new BaiduTranslator(config);
    }

    public AliyunTranslator getAliyunTranslator() {
        return (AliyunTranslator) this.translatorMap.get(TranslatorType.Aliyun);
    }

    public AliyunTranslator getAliyunTranslator(AliyunTranslator.Config config) {
        return new AliyunTranslator(config);
    }

}
