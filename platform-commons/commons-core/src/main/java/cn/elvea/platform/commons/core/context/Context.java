package cn.elvea.platform.commons.core.context;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
public class Context implements Serializable {

    @Builder.Default
    private boolean debugEnabled = false;

    @Builder.Default
    private boolean amqpEnabled = false;

}
