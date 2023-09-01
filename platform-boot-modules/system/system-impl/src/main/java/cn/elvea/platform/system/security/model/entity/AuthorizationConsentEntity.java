package cn.elvea.platform.system.security.model.entity;

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
 * @since 0.0.1
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
