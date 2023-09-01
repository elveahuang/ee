package cn.elvea.platform.lxp.core.model.entity;

import cn.elvea.platform.commons.core.data.jpa.domain.BaseEntity;
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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lxp_project")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class ProjectEntity extends BaseEntity {
    private String code;
    private String title;
}
