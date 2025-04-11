package cc.elvea.platform.commons.autoconfigure.extensions;

import cc.elvea.platform.commons.autoconfigure.extensions.properties.IpProperties;
import cc.elvea.platform.commons.extensions.ip.GeoLite;
import cc.elvea.platform.commons.extensions.ip.GlobalIpManager;
import cc.elvea.platform.commons.extensions.ip.LocationEnum;
import cc.elvea.platform.commons.utils.StringUtils;
import com.maxmind.db.Metadata;
import com.maxmind.db.Network;
import com.maxmind.db.Networks;
import com.maxmind.db.Reader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeHint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.function.Consumer;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({IpProperties.class})
@ConditionalOnProperty(prefix = IpProperties.PREFIX, name = "enabled", havingValue = "true")
@ImportRuntimeHints({IpAutoConfiguration.IpRuntimeHints.class})
public class IpAutoConfiguration {

    public IpAutoConfiguration(IpProperties properties) {
        log.info("IpAutoConfiguration is enabled.");
        log.info("GeoLite location - {}.", properties.getGeoLite().getLocation());
        log.info("GeoLite path - {}.", properties.getGeoLite().getPath());
    }

    @Bean
    @ConditionalOnMissingBean
    public GeoLite geoLite(IpProperties properties) {
        System.out.println("Create geoLite.");

        GeoLite geoLite = new GeoLite();
        if (StringUtils.isNotEmpty(properties.getGeoLite().getPath())) {
            Resource resource;
            if (LocationEnum.CLASSPATH.equals(properties.getGeoLite().getLocation())) {
                resource = new ClassPathResource(properties.getGeoLite().getPath());
            } else {
                resource = new FileSystemResource(properties.getGeoLite().getPath());
            }
            geoLite.init(resource);
        }

        GlobalIpManager.setGeoLite(geoLite);
        return geoLite;
    }

    public static class IpRuntimeHints implements RuntimeHintsRegistrar {

        private static final Consumer<TypeHint.Builder> INVOKE_DECLARED_CONSTRUCTORS = TypeHint.builtWith(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.reflection().registerType(Metadata.class, INVOKE_DECLARED_CONSTRUCTORS);
            hints.reflection().registerType(Network.class, INVOKE_DECLARED_CONSTRUCTORS);
            hints.reflection().registerType(Networks.class, INVOKE_DECLARED_CONSTRUCTORS);
            hints.reflection().registerType(Reader.class, INVOKE_DECLARED_CONSTRUCTORS);
        }

    }

}
