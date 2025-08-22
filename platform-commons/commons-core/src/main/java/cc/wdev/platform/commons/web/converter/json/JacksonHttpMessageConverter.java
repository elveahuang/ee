package cc.wdev.platform.commons.web.converter.json;

import cc.wdev.platform.commons.utils.JacksonUtils;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author elvea
 */
public class JacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public JacksonHttpMessageConverter() {
        super(JacksonUtils.getObjectMapper());
    }

}
