package cc.wdev.platform.commons.ai.model.config;

import cc.wdev.platform.commons.ai.model.ModelConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleModelConfig implements ModelConfig {
    private String name;
    private String apiKey;
    private String baseUrl;
    private String providerCode;
    private String modelType;
    @Builder.Default
    private Map<String, Object> variables = new HashMap<>();
    @Builder.Default
    private Boolean returnThinking = false;
    @Builder.Default
    private Boolean enableWebSearch = false;
}
