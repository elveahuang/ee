package top.baihu.platform.system.core.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import top.baihu.platform.commons.annotations.DateTimeFormat;
import top.baihu.platform.commons.annotations.JsonFormat;
import top.baihu.platform.commons.data.jpa.domain.BaseEntity;

import java.time.LocalDateTime;

import static top.baihu.platform.commons.constants.DateTimeConstants.DEFAULT_DATE_TIME_PATTERN;

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
