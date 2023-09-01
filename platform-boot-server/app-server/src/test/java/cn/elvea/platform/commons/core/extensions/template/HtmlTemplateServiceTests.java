package cn.elvea.platform.commons.core.extensions.template;

import cn.elvea.platform.BaseTests;
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
 * @since 0.0.1
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
