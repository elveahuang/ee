package cc.wdev.platform.commons.core.ai.tencent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public class AiTencentFactoryImpl implements AiTencentFactory {

    private final AiTencentConfig config;

    public AiTencentFactoryImpl(AiTencentConfig config) {
        this.config = config;
    }

    private AiTencentConfig getConfig() {
        return this.config;
    }

}
