package cc.wdev.platform.commons.core.ai.tencent;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
public class AiTencentConfig implements Serializable {

    private String apiKey;

}
