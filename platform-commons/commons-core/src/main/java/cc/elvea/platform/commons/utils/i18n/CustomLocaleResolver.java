package cc.elvea.platform.commons.utils.i18n;

import cc.elvea.platform.commons.constants.GlobalConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * @author elvea
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
