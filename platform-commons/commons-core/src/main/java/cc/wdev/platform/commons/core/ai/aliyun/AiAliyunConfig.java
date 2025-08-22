package cc.wdev.platform.commons.core.ai.aliyun;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
public class AiAliyunConfig implements Serializable {
    private String apiKey;
}
