package cc.elvea.platform.security.annotations;

import cc.elvea.platform.security.configuration.AuthorizationServerConfiguration;
import cc.elvea.platform.security.configuration.CommonSecurityConfiguration;
import org.springframework.context.annotation.Import;

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
