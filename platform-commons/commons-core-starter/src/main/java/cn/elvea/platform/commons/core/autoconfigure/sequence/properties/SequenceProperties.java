package cn.elvea.platform.commons.core.autoconfigure.sequence.properties;

import cn.elvea.platform.commons.core.constants.DateTimeConstants;
import cn.elvea.platform.commons.core.sequence.SequenceType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = SequenceProperties.PREFIX)
public class SequenceProperties implements Serializable {

    public static final String PREFIX = "platform.sequence";

    private SequenceType type = SequenceType.Manual;

    private Boolean enabled = Boolean.FALSE;

    private Long epoch = DateTimeConstants.EPOCH;

    private Long datacenterId = 1L;

    private Long workerId = 1L;

    private Long id = 1L;

}
