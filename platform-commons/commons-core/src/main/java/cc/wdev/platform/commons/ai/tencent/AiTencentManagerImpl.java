package cc.wdev.platform.commons.ai.tencent;

import cc.wdev.platform.commons.ai.config.ProviderConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public record AiTencentManagerImpl(ProviderConfig config) implements AiTencentManager {

    /**
     * @see AiTencentManager#getConfig()
     */
    @Override
    public ProviderConfig getConfig() {
        return this.config;
    }

}
