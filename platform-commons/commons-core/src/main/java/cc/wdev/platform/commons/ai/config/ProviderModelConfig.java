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
public class ProviderModelConfig {

    private String chat;

    private String image;

    private String embedding;

    private String transcription;

}
