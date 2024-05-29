package cc.elvea.platform.commons.extensions.i18n;

/**
 * 默认语言解析器，直接返回当前系统的默认时区。
 *
 * @author elvea
 * @since 24.1.0
 */
public record DefaultLanguageResolver(String language) implements LanguageResolver {

    @Override
    public String resolveLanguage() {
        return this.language;
    }

}
