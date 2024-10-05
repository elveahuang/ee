package cc.elvea.platform.commons.template;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.core.template.HtmlTemplateService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class HtmlTemplateServiceTests extends BaseTests {

    @Autowired
    private HtmlTemplateService htmlTemplateService;

    @Test
    public void base() throws Exception {
        org.springframework.core.io.ClassPathResource resource = new ClassPathResource("html/tpl.html");
        String template = resource.getContentAsString(StandardCharsets.UTF_8);

        Map<String, Object> params = Maps.newHashMap();
        params.put("title", "Title");
        params.put("content", "Content Content");
        String html = htmlTemplateService.toHtml(template, params);
        Assertions.assertNotNull(html);

    }

}
