package cc.elvea.platform.system.attachment.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_attachment_type")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class AttachmentTypeEntity extends BaseEntity {
    private String code;
    private String title;
    private String label;
    private String description;
    private Integer source;
}
