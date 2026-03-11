package cc.wdev.platform.commons.core.ai.aliyun;

import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public record AiAliyunManagerImpl(AiAliyunConfig config) implements AiAliyunManager {

    /**
     * @see AiAliyunManager#getConfig()
     */
    @Override
    public AiAliyunConfig getConfig() {
        return this.config;
    }

}
