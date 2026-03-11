package cc.wdev.platform.commons.core.ai.tencent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public record AiTencentManagerImpl(AiTencentConfig config) implements AiTencentManager {

    /**
     * @see AiTencentManager#getConfig()
     */
    @Override
    public AiTencentConfig getConfig() {
        return this.config;
    }

}
