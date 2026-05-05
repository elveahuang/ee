package cc.wdev.platform.system.message.domain.entity;

import cc.wdev.platform.commons.data.jpa.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_message_history")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class MessageHistoryEntity extends BaseEntity {
    /**
     * 消息类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;
    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    /**
     * 消息ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long messageId;
    /**
     * 目标ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long targetId;
    /**
     * 目标类型
     */
    private String targetType;
}
