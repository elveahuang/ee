package cc.elvea.platform.system.announcement.model.entity;

import cc.elvea.platform.commons.annotations.DateTimeFormat;
import cc.elvea.platform.commons.annotations.JsonFormat;
import cc.elvea.platform.commons.data.jpa.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static cc.elvea.platform.commons.constants.DateTimeConstants.DEFAULT_DATE_TIME_PATTERN;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_announcement")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class AnnouncementEntity extends BaseEntity {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 备注
     */
    private String description;
    /**
     * 发布状态
     */
    private Integer status;
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
