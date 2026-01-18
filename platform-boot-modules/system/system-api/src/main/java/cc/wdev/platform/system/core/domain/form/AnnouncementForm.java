package cc.wdev.platform.system.core.domain.form;

import cc.wdev.platform.commons.annotations.DateTimeFormat;
import cc.wdev.platform.commons.annotations.JsonFormat;
import cc.wdev.platform.commons.enums.StatusTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cc.wdev.platform.commons.constants.DateTimeConstants.DEFAULT_DATE_TIME_PATTERN;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementForm implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;
    /**
     * 标题
     */
    @NotEmpty
    private String title;
    /**
     * 内容
     */
    @NotEmpty
    private String content;
    /**
     * 备注
     */
    private String description;
    /**
     * 发布状态
     */
    @Builder.Default
    private Integer status = StatusTypeEnum.ON.getValue();
    /**
     * 发布期限-开始时间
     */
    @JsonFormat(pattern = DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime startDatetime;
    /**
     * 发布期限-结束时间
     */
    @JsonFormat(pattern = DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime endDatetime;
}
