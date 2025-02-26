package cc.elvea.platform.system.core.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.AbstractEntity;
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
@Table(name = "sys_v_actor")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class OperatorEntity extends AbstractEntity {

    private String type;

    private String username;

}
