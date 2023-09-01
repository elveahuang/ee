package cn.elvea.platform.commons.core.extensions.translator;

import cn.elvea.platform.commons.core.enums.LangTypeEnum;

/**
 * 翻译器
 *
 * @author elvea
 * @since 0.0.1
 */
public interface Translator {

    /**
     * 翻译
     *
     * @param sourceLang 源语言
     * @param targetLang 目标语言
     * @param text       文本
     * @return 翻译文本
     */
    String translate(String sourceLang, String targetLang, String text);

    /**
     * 翻译
     *
     * @param sourceLang 源语言
     * @param targetLang 目标语言
     * @param text       文本
     * @return 翻译文本
     */
    default String translate(LangTypeEnum sourceLang, LangTypeEnum targetLang, String text) {
        return this.translate(sourceLang.getValue(), targetLang.getValue(), text);
    }

}
