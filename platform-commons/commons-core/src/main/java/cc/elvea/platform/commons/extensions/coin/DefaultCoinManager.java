package cc.elvea.platform.commons.extensions.coin;

import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public record DefaultCoinManager(CoinConfig config) implements CoinManager {
}
