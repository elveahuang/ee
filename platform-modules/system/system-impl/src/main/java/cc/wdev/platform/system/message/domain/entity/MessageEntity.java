package cc.wdev.platform.system.message.domain.entity;

import cc.wdev.platform.commons.annotations.DateTimeFormat;
import cc.wdev.platform.commons.annotations.JsonFormat;
import cc.wdev.platform.commons.constants.DateTimeConstants;
import cc.wdev.platform.commons.data.jpa.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_message")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class MessageEntity extends BaseEntity {
    /**
     * 消息类型ID
     */
    @NonNull
    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;
    /**
     * 目标ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long targetId;
    /**
     * 目标类型
     */
    private String targetType;
    /**
     * 标题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
    /**
     * 数据
     */
    private String data;
    /**
     * 发布状态
     */
    private Integer status;
    /**
     * 目标发送时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_FULL_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_FULL_DATE_TIME_PATTERN)
    private LocalDateTime targetSentDatetime;
    /**
     * 发送时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_FULL_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_FULL_DATE_TIME_PATTERN)
    private LocalDateTime sentDatetime;
    /**
     * 尝试发送次数
     */
    private Integer attempt;
}
