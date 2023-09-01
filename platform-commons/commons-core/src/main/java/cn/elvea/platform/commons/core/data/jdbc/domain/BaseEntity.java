package cn.elvea.platform.commons.core.data.jdbc.domain;

import cn.elvea.platform.commons.core.annotations.DateTimeFormat;
import cn.elvea.platform.commons.core.annotations.JsonFormat;
import cn.elvea.platform.commons.core.constants.DateTimeConstants;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity extends AbstractEntity {
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
    /**
     * 最后修改时间
     */
    @LastModifiedDate
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime lastModifiedAt;
    /**
     * 最后修改人
     */
    @LastModifiedBy
    @JsonSerialize(using = ToStringSerializer.class)
    private Long lastModifiedBy;
    /**
     * 删除时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime deletedAt;
    /**
     * 删除人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deletedBy;
}
