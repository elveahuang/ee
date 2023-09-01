package cn.elvea.platform.commons.core.extensions.i18n;

/**
 * 默认语言解析器，直接返回当前系统的默认时区。
 *
 * @author elvea
 * @since 0.0.1
 */
public record DefaultLanguageResolver(String language) implements LanguageResolver {

    @Override
    public String resolveLanguage() {
        return this.language;
    }

}
