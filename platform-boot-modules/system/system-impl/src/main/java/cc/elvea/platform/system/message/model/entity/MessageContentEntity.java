package cc.elvea.platform.system.message.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "sys_message_content")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class MessageContentEntity extends BaseEntity {
    /**
     * 消息ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long messageId;
    /**
     * 模板类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long templateTypeId;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 响应内容
     */
    private String resp;
    /**
     * 异常内容
     */
    private String exception;
    /**
     * 发送时间
     */
    private LocalDateTime sentDatetime;
    /**
     * 发送状态
     */
    private Integer status;
}
