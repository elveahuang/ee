package cc.wdev.dev.webapp.configuration;

import cc.wdev.platform.commons.core.Context;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Map;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final Context context;

    private final ThymeleafViewResolver thymeleafViewResolver;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/web/home");
    }

    @Override
    public void configureViewResolvers(@Nullable ViewResolverRegistry registry) {
        Map<String, String> vars = Maps.newHashMap();
        vars.put("applicationName", context.getApplicationName());
        vars.put("applicationVersion", context.getApplicationVersion());
        this.thymeleafViewResolver.setStaticVariables(vars);
    }

}
