package cc.elvea.platform.auth.config;

import cc.elvea.platform.commons.constants.GlobalConstants;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Map;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class WebMvcConfiguration implements WebMvcConfigurer {

    private ThymeleafViewResolver thymeleafViewResolver;

    @Override
    public void configureViewResolvers(@Nullable ViewResolverRegistry registry) {
        if (this.thymeleafViewResolver != null) {
            Map<String, String> vars = Maps.newHashMap();
            vars.put("applicationVersion", GlobalConstants.VERSION);
            vars.put("applicationTitle", "Application");
            this.thymeleafViewResolver.setStaticVariables(vars);
        }
    }

    @Autowired(required = false)
    public void setThymeleafViewResolver(ThymeleafViewResolver thymeleafViewResolver) {
        this.thymeleafViewResolver = thymeleafViewResolver;
    }

}
