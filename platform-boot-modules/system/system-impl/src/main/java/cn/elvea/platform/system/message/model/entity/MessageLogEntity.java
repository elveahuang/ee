package cn.elvea.platform.system.message.model.entity;

import cn.elvea.platform.commons.core.data.jpa.domain.SimpleEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author elvea
 * @since 0.0.1
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_message_log")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class MessageLogEntity extends SimpleEntity {
    /**
     * 消息ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long messageId;
    /**
     * 日志内容
     */
    private String details;
    /**
     * 日志异常
     */
    private String exception;
}
