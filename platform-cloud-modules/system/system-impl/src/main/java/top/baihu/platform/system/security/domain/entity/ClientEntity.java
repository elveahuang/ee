package top.baihu.platform.system.security.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.baihu.platform.commons.data.mybatis.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_client")
public class ClientEntity extends BaseEntity {
    private String clientId;
    private LocalDateTime clientIdIssuedAt;
    private String clientSecret;
    private LocalDateTime clientSecretExpiresAt;
    private String clientName;
    private String clientAuthenticationMethods;
    private String authorizationGrantTypes;
    private String postLogoutRedirectUris;
    private String redirectUris;
    private String scopes;
    private String clientSettings;
    private String tokenSettings;
    private String description;
}
