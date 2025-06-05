package cc.elvea.platform.system.security.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.BaseEntity;
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
@Table(name = "sys_authorization")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class AuthorizationEntity extends BaseEntity {
    /**
     *
     */
    private String uuid;
    /**
     *
     */
    private String clientId;
    private String principalName;
    private String authorizationGrantType;
    private String attributes;
    /**
     *
     */
    private String state;
    /**
     *
     */
    private String authorizationCodeValue;
    private LocalDateTime authorizationCodeIssuedAt;
    private LocalDateTime authorizationCodeExpiresAt;
    private String authorizationCodeMetadata;
    /**
     *
     */
    private String accessTokenValue;
    private LocalDateTime accessTokenIssuedAt;
    private LocalDateTime accessTokenExpiresAt;
    private String accessTokenMetadata;
    private String accessTokenType;
    private String accessTokenScopes;
    /**
     *
     */
    private String oidcIdTokenValue;
    private LocalDateTime oidcIdTokenIssuedAt;
    private LocalDateTime oidcIdTokenExpiresAt;
    private String oidcIdTokenMetadata;
    private String oidcIdTokenClaims;
    /**
     *
     */
    private String refreshTokenValue;
    private LocalDateTime refreshTokenIssuedAt;
    private LocalDateTime refreshTokenExpiresAt;
    private String refreshTokenMetadata;
}
