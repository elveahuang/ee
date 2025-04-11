package cc.elvea.platform.system.security.model.dto;

import cc.elvea.platform.commons.annotations.DateTimeFormat;
import cc.elvea.platform.commons.annotations.JsonFormat;
import cc.elvea.platform.commons.constants.DateTimeConstants;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class AuthorizationDto implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String uuid;
    private String clientId;
    private String principalName;
    private String authorizationGrantType;
    private String attributes;
    private String state;

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * Authorization Code
     * -----------------------------------------------------------------------------------------------------------------
     */

    private String authorizationCodeValue;
    private String authorizationCodeMetadata;
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime authorizationCodeIssuedAt;
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime authorizationCodeExpiresAt;

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * OIDC ID Token
     * -----------------------------------------------------------------------------------------------------------------
     */

    private String accessTokenValue;
    private String accessTokenMetadata;
    private String accessTokenType;
    private String accessTokenScopes;
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime accessTokenIssuedAt;
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime accessTokenExpiresAt;

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * OIDC ID Token
     * -----------------------------------------------------------------------------------------------------------------
     */

    private String oidcIdTokenValue;
    private String oidcIdTokenMetadata;
    private String oidcIdTokenClaims;
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime oidcIdTokenIssuedAt;
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime oidcIdTokenExpiresAt;

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * Refresh Token
     * -----------------------------------------------------------------------------------------------------------------
     */

    private String refreshTokenValue;
    private String refreshTokenMetadata;
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime refreshTokenIssuedAt;
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime refreshTokenExpiresAt;

}
