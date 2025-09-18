package cc.wdev.platform.commons.core.ai.aliyun;

import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public record AiAliyunFactoryImpl(AiAliyunConfig config) implements AiAliyunFactory {

    /**
     * @see AiAliyunFactory#getConfig()
     */
    @Override
    public AiAliyunConfig getConfig() {
        return this.config;
    }

}
