package cc.wdev.platform.commons.core.ai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
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
    private List<Object> tools = List.of();

    public static AiCustomizer defaultCustomizer() {
        return AiCustomizer.builder().tools(Collections.emptyList()).build();
    }

}
