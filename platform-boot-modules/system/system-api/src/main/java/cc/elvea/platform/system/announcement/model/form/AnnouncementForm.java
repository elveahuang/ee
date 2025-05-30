package cc.elvea.platform.system.announcement.model.form;

import cc.elvea.platform.commons.annotations.DateTimeFormat;
import cc.elvea.platform.commons.annotations.JsonFormat;
import cc.elvea.platform.commons.enums.PublishStatusTypeEnum;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cc.elvea.platform.commons.constants.DateTimeConstants.DEFAULT_DATE_TIME_PATTERN;

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
    private Integer status = PublishStatusTypeEnum.OFF.getValue();
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
