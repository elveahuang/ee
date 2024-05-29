package cc.elvea.platform.commons.oapis.translator;

import cc.elvea.platform.commons.enums.LangTypeEnum;

/**
 * 翻译器
 *
 * @author elvea
 * @since 24.1.0
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
