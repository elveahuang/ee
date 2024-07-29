package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.IpProperties;
import cc.elvea.platform.commons.extensions.ip.GeoLite;
import cc.elvea.platform.commons.extensions.ip.GlobalIpManager;
import cc.elvea.platform.commons.extensions.ip.IpRegion;
import cc.elvea.platform.commons.extensions.ip.LocationEnum;
import cc.elvea.platform.commons.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = IpProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties({IpProperties.class})
public class IpConfiguration {

    public IpConfiguration() {
        log.info("IpConfiguration is enabled.");
    }

    /**
     * @return {@link IpRegion}
     */
    @Bean
    @ConditionalOnMissingBean
    public IpRegion ipRegion(IpProperties properties) {
        IpRegion ipRegion = new IpRegion();

        if (StringUtils.isNotEmpty(properties.getRegion().getPath())) {
            Resource resource;
            if (properties.getRegion().getLocation().equals(LocationEnum.CLASSPATH)) {
                resource = new ClassPathResource(properties.getRegion().getPath());
            } else {
                resource = new FileSystemResource(properties.getRegion().getPath());
            }
            ipRegion.init(resource);
        }

        GlobalIpManager.setIpRegion(ipRegion);
        return ipRegion;
    }

    /**
     * @return {@link GeoLite}
     */
    @Bean
    @ConditionalOnMissingBean
    public GeoLite geoLite(IpProperties properties) {
        GeoLite geoLite = new GeoLite();

        if (StringUtils.isNotEmpty(properties.getGeoLite().getPath())) {
            Resource resource;
            if (properties.getGeoLite().getLocation().equals(LocationEnum.CLASSPATH)) {
                resource = new ClassPathResource(properties.getGeoLite().getPath());
            } else {
                resource = new FileSystemResource(properties.getGeoLite().getPath());
            }
            geoLite.init(resource);
        }

        GlobalIpManager.setGeoLite(geoLite);
        return geoLite;
    }

}
