package cc.elvea.platform.commons.extensions.coin;

import io.goodforgod.api.etherscan.EtherScanAPI;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public record DefaultCoinManager(CoinConfig config) implements CoinManager {

    /**
     * @see CoinManager#getEtherScanAPI()
     */
    @Override
    public EtherScanAPI getEtherScanAPI() {
        return this.getEtherScanAPI(this.config);
    }

    /**
     * @see CoinManager#getEtherScanAPI(CoinConfig)
     */
    @Override
    public EtherScanAPI getEtherScanAPI(CoinConfig config) {
        return EtherScanAPI.builder()
                .withApiKey(config.getEtherScan().getApiKey())
                .build();
    }

}
