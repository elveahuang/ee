package cn.elvea.platform.config;

import cn.elvea.platform.commons.core.constants.GlobalConstants;
import cn.elvea.platform.system.commons.interceptor.LogInterceptor;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Map;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;

    private final ThymeleafViewResolver thymeleafViewResolver;

    @Override
    public void configureViewResolvers(@Nullable ViewResolverRegistry registry) {
        Map<String, String> vars = Maps.newHashMap();
        vars.put("applicationVersion", GlobalConstants.VERSION);
        vars.put("applicationTitle", "Application");
        this.thymeleafViewResolver.setStaticVariables(vars);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.logInterceptor);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
