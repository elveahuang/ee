package top.baihu.platform.system.message.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import top.baihu.platform.commons.data.jpa.domain.BaseEntity;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_message_template_type")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class MessageTemplateTypeEntity extends BaseEntity {
    /**
     * 编号
     */
    private String code;
    /**
     * 标题
     */
    private String title;
    /**
     * 标题
     */
    private String label;
    /**
     * 说明
     */
    private String description;
    /**
     * 发布状态
     */
    private Integer status;
}
