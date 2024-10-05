package cc.elvea.platform.commons.core.template;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
import java.util.Map;

/**
 * @author elvea
 * @since 24.1.0
 */
public class DefaultHtmlTemplateService implements HtmlTemplateService {

    private final TemplateEngine templateEngine;

    public DefaultHtmlTemplateService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String toHtml(String template, Map<String, Object> params) {
        Context context = new Context(Locale.getDefault(), params);
        return templateEngine.process(template, context);
    }

}
