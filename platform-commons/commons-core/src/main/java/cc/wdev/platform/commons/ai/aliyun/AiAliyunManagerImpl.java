package cc.wdev.platform.commons.ai.aliyun;

import cc.wdev.platform.commons.ai.config.ProviderConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public record AiAliyunManagerImpl(ProviderConfig config) implements AiAliyunManager {

    /**
     * @see AiAliyunManager#getConfig()
     */
    @Override
    public ProviderConfig getConfig() {
        return this.config;
    }

}
