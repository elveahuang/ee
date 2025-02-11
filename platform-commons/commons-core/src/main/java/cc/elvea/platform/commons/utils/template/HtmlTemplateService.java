package cc.elvea.platform.commons.utils.template;

import java.util.Map;

/**
 * @author elvea
 */
public interface HtmlTemplateService {

    String toHtml(String template, Map<String, Object> params);

}
