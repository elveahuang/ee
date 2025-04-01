package cc.elvea.platform.commons.autoconfigure.extensions;

import cc.elvea.platform.commons.autoconfigure.extensions.properties.CoinProperties;
import cc.elvea.platform.commons.extensions.coin.CoinConfig;
import cc.elvea.platform.commons.extensions.coin.CoinManager;
import cc.elvea.platform.commons.extensions.coin.DefaultCoinManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CoinProperties.class})
@ConditionalOnProperty(prefix = CoinProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class CoinAutoConfiguration {

    public CoinAutoConfiguration() {
        log.info("CoinAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public CoinManager coinManager(CoinProperties properties) {
        CoinConfig config = CoinConfig.builder()
                .etherScan(properties.getEtherscan())
                .coinGecko(properties.getCoinGecko())
                .coinMarketCap(properties.getCoinMarketCap())
                .cryptoRank(properties.getCryptoRank())
                .alchemy(properties.getAlchemy())
                .build();
        return new DefaultCoinManager(config);
    }

}
