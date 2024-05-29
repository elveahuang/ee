package cc.elvea.platform.commons.data.jdbc.domain;

import cc.elvea.platform.commons.annotations.DateTimeFormat;
import cc.elvea.platform.commons.annotations.JsonFormat;
import cc.elvea.platform.commons.constants.DateTimeConstants;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class SimpleEntity extends AbstractEntity {
    /**
     * 启用状态
     */
    private Boolean active;
    /**
     * 创建时间
     */
    @CreatedDate
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    @CreatedBy
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;
}
