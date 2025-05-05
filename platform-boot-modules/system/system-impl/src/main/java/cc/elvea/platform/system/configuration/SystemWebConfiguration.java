package cc.elvea.platform.system.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
public class SystemWebConfiguration implements WebMvcConfigurer {
}
