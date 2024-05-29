package cc.elvea.platform.commons.extensions.coin;

import io.goodforgod.api.etherscan.EtherScanAPI;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class DefaultCoinManager implements CoinManager {

    private final CoinConfig config;

    public DefaultCoinManager(CoinConfig config) {
        this.config = config;
    }

    /**
     * @see CoinManager#getEtherScanAPI()
     */
    @Override
    public EtherScanAPI getEtherScanAPI() {
        return EtherScanAPI.builder()
                .withApiKey(config.getEtherScanApiKey())
                .build();
    }

    /**
     * @see CoinManager#getEtherScanAPI(CoinConfig)
     */
    @Override
    public EtherScanAPI getEtherScanAPI(CoinConfig config) {
        return EtherScanAPI.builder()
                .withApiKey(config.getEtherScanApiKey())
                .build();
    }

}
