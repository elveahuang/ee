package cc.wdev.platform.commons.core.ai.aliyun;

import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public class AiAliyunFactoryImpl implements AiAliyunFactory {

    private final AiAliyunConfig config;

    public AiAliyunFactoryImpl(AiAliyunConfig config) {
        this.config = config;
    }

    private AiAliyunConfig getConfig() {
        return this.config;
    }

}
