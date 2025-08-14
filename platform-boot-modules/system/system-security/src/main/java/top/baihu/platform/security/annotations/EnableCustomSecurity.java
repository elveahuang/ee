package top.baihu.platform.security.annotations;

import org.springframework.context.annotation.Import;
import top.baihu.platform.security.configuration.AuthorizationServerConfiguration;
import top.baihu.platform.security.configuration.CommonSecurityConfiguration;

import java.lang.annotation.*;

/**
 * 权限注解，允许匿名访问
 *
 * @author elvea
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({
    AuthorizationServerConfiguration.class,
    CommonSecurityConfiguration.class,
})
public @interface EnableCustomSecurity {
}
