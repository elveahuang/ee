package cc.elvea.platform.system.security.model.entity;

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
 * @since 24.1.0
 * @since 24.1.0
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_authorization_consent")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class AuthorizationConsentEntity extends BaseEntity {
    /**
     *
     */
    private String uuid;
    /**
     *
     */
    private String clientId;
    /**
     *
     */
    private String principalName;
    /**
     *
     */
    private String authorities;
}
