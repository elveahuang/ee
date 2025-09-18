package cc.wdev.platform.commons.core.ai.aliyun;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiAliyunConfig implements Serializable {

    private String apiKey;

    @Builder.Default
    private AiAliyunConfig.Transcription transcription = AiAliyunConfig.Transcription.builder().build();

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Transcription implements Serializable {
        @Builder.Default
        private String model = "paraformer-v2";
    }

}
