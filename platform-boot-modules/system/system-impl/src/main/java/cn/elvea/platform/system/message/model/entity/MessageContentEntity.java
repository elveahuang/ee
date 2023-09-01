package cn.elvea.platform.system.message.model.entity;

import cn.elvea.platform.commons.core.data.jpa.domain.BaseEntity;
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
     * 发送时间
     */
    private String sentDatetime;
    /**
     * 发送状态
     */
    private Integer status;
}
