package cc.wdev.platform.commons.core.ai;

import cc.wdev.platform.commons.core.ai.tools.CommonTools;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiCustomizer {

    @Builder.Default
    private String defaultSystem = "";

    @Builder.Default
    private List<Object> tools = List.of(new CommonTools());

    public static AiCustomizer defaultCustomizer() {
        return AiCustomizer.builder().tools(List.of(new CommonTools())).build();
    }

}
