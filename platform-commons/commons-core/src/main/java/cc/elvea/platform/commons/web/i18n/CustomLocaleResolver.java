package cc.elvea.platform.commons.web.i18n;

import cc.elvea.platform.commons.constants.GlobalConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * 全局异常处理器
 *
 * @author elvea
 * @since 24.1.0
 */
public class CustomLocaleResolver implements LocaleResolver {

    @Override
    @NonNull
    public Locale resolveLocale(@NonNull HttpServletRequest request) {
        return GlobalConstants.DEFAULT_LOCALE;
    }

    @Override
    public void setLocale(@NonNull HttpServletRequest request, HttpServletResponse response, Locale locale) {
    }

}
