package cc.elvea.platform.configuration;

import cc.elvea.platform.commons.constants.GlobalConstants;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Map;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final ThymeleafViewResolver thymeleafViewResolver;

    @Override
    public void configureViewResolvers(@Nullable ViewResolverRegistry registry) {
        Map<String, String> vars = Maps.newHashMap();
        vars.put("applicationVersion", GlobalConstants.VERSION);
        vars.put("applicationTitle", "Application");
        this.thymeleafViewResolver.setStaticVariables(vars);
    }

}
