package cn.elvea.platform.commons.core.extensions.translator;

/**
 * 语言转换器
 *
 * @author elvea
 * @since 0.0.1
 */
public interface TranslatorConverter {

    /**
     * 把语言转换成当前翻译器支持的语言编号，默认直接返回
     *
     * @param language 语言
     * @return 转换后的语言编号
     */
    default String convert(String language) {
        return language;
    }

}
