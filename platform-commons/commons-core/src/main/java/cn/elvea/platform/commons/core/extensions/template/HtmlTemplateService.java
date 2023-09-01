package cn.elvea.platform.commons.core.extensions.template;

import java.util.Map;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface HtmlTemplateService {

    String toHtml(String template, Map<String, Object> params);

}
