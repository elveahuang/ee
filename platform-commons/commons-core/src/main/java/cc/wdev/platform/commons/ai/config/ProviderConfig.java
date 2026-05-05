package cc.wdev.platform.commons.ai.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProviderConfig {

    private String apiKey;

    private String baseUrl;

    private ProviderModelConfig models;

}
