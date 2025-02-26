package cc.elvea.platform.commons.extensions.coin;

import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public record DefaultCoinManager(CoinConfig config) implements CoinManager {
}
