package cc.elvea.platform.commons.autoconfigure.core.properties;

import cc.elvea.platform.commons.constants.DateTimeConstants;
import cc.elvea.platform.commons.core.sequence.SequenceType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = SequenceProperties.PREFIX)
public class SequenceProperties implements Serializable {

    public static final String PREFIX = "platform.sequence";

    private boolean enabled = false;

    private SequenceType type = SequenceType.AUTO;

    private Long epoch = DateTimeConstants.EPOCH;

    private Long datacenterId = 1L;

    private Long workerId = 1L;

    private Long id = 1L;

}
