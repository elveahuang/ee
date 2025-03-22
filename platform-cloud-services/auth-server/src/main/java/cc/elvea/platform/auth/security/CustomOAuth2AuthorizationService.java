package cc.elvea.platform.auth.security;

import cc.elvea.platform.auth.security.utils.OAuth2Utils;
import cc.elvea.platform.system.security.api.AuthorizationApi;
import cc.elvea.platform.system.security.api.ClientApi;
import cc.elvea.platform.system.security.model.dto.AuthorizationDto;
import cc.elvea.platform.system.security.model.dto.ClientDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class CustomOAuth2AuthorizationService implements OAuth2AuthorizationService {

    private final AuthorizationApi authorizationApi;

    private final ClientApi clientApi;

    @Override
    public void save(OAuth2Authorization authorization) {
        this.authorizationApi.save(toEntity(authorization));
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        this.authorizationApi.deleteById(Long.valueOf(authorization.getId()));
    }

    @Override
    public OAuth2Authorization findById(String id) {
        return toObject(this.authorizationApi.findById(Long.valueOf(id)).getData());
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        AuthorizationDto authorization = null;
        if (tokenType != null) {
            if (OAuth2ParameterNames.STATE.equals(tokenType.getValue())) {
                authorization = this.authorizationApi.findByState(token).getData();
            } else if (OAuth2ParameterNames.CODE.equals(tokenType.getValue())) {
                authorization = this.authorizationApi.findByAuthorizationCodeValue(token).getData();
            } else if (OAuth2ParameterNames.ACCESS_TOKEN.equals(tokenType.getValue())) {
                authorization = this.authorizationApi.findByAccessTokenValue(token).getData();
            } else if (OAuth2ParameterNames.REFRESH_TOKEN.equals(tokenType.getValue())) {
                authorization = this.authorizationApi.findByRefreshTokenValue(token).getData();
            }
        }
        return toObject(authorization);
    }

    private OAuth2Authorization toObject(AuthorizationDto dto) {
        if (dto != null) {
            ClientDto clientDto = this.clientApi.findByClientId(dto.getClientId()).getData();
            if (clientDto == null) {
                throw new DataRetrievalFailureException("Invalid Client with id '" + dto.getClientId() + "'.");
            }
            RegisteredClient client = OAuth2Utils.toRegisteredClient(clientDto);
            OAuth2Authorization.Builder builder = OAuth2Authorization.withRegisteredClient(client)
                    .id(String.valueOf(dto.getUuid()))
                    .principalName(dto.getPrincipalName())
                    .authorizationGrantType(OAuth2Utils.resolveAuthorizationGrantType(dto.getAuthorizationGrantType()))
                    .attributes(attributes -> attributes.putAll(OAuth2Utils.parseMap(dto.getAttributes())));
            if (dto.getState() != null) {
                builder.attribute(OAuth2ParameterNames.STATE, dto.getState());
            }

            if (dto.getAuthorizationCodeValue() != null) {
                OAuth2AuthorizationCode authorizationCode = new OAuth2AuthorizationCode(
                        dto.getAuthorizationCodeValue(),
                        dto.getAuthorizationCodeIssuedAt().toInstant(ZoneOffset.UTC),
                        dto.getAuthorizationCodeExpiresAt().toInstant(ZoneOffset.UTC));
                builder.token(authorizationCode, metadata -> metadata.putAll(OAuth2Utils.parseMap(dto.getAuthorizationCodeMetadata())));
            }

            if (dto.getAccessTokenValue() != null) {
                OAuth2AccessToken accessToken = new OAuth2AccessToken(
                        OAuth2AccessToken.TokenType.BEARER,
                        dto.getAccessTokenValue(),
                        dto.getAccessTokenIssuedAt().toInstant(ZoneOffset.UTC),
                        dto.getAccessTokenExpiresAt().toInstant(ZoneOffset.UTC),
                        StringUtils.commaDelimitedListToSet(dto.getAccessTokenScopes()));
                builder.token(accessToken, metadata -> metadata.putAll(OAuth2Utils.parseMap(dto.getAccessTokenMetadata())));
            }

            if (dto.getRefreshTokenValue() != null) {
                OAuth2RefreshToken refreshToken = new OAuth2RefreshToken(
                        dto.getRefreshTokenValue(),
                        dto.getRefreshTokenIssuedAt().toInstant(ZoneOffset.UTC),
                        dto.getRefreshTokenExpiresAt().toInstant(ZoneOffset.UTC));
                builder.token(refreshToken, metadata -> metadata.putAll(OAuth2Utils.parseMap(dto.getRefreshTokenMetadata())));
            }

            if (dto.getOidcIdTokenValue() != null) {
                OidcIdToken idToken = new OidcIdToken(
                        dto.getOidcIdTokenValue(),
                        dto.getOidcIdTokenIssuedAt().toInstant(ZoneOffset.UTC),
                        dto.getOidcIdTokenExpiresAt().toInstant(ZoneOffset.UTC),
                        OAuth2Utils.parseMap(dto.getOidcIdTokenClaims()));
                builder.token(idToken, metadata -> metadata.putAll(OAuth2Utils.parseMap(dto.getOidcIdTokenMetadata())));
            }

            return builder.build();
        }
        return null;
    }

    public AuthorizationDto toEntity(OAuth2Authorization authorization) {
        AuthorizationDto entity = new AuthorizationDto();
        entity.setUuid(authorization.getId());
        entity.setClientId(authorization.getRegisteredClientId());
        entity.setPrincipalName(authorization.getPrincipalName());
        entity.setAuthorizationGrantType(authorization.getAuthorizationGrantType().getValue());
        entity.setAttributes(OAuth2Utils.writeMap(authorization.getAttributes()));
        entity.setState(authorization.getAttribute(OAuth2ParameterNames.STATE));

        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization.getToken(OAuth2AuthorizationCode.class);
        setTokenValues(authorizationCode,
                entity::setAuthorizationCodeValue,
                entity::setAuthorizationCodeIssuedAt,
                entity::setAuthorizationCodeExpiresAt,
                entity::setAuthorizationCodeMetadata
        );

        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getToken(OAuth2AccessToken.class);
        setTokenValues(accessToken,
                entity::setAccessTokenValue,
                entity::setAccessTokenIssuedAt,
                entity::setAccessTokenExpiresAt,
                entity::setAccessTokenMetadata
        );

        if (accessToken != null && accessToken.getToken().getScopes() != null) {
            entity.setAccessTokenScopes(org.springframework.util.StringUtils.collectionToDelimitedString(accessToken.getToken().getScopes(), ","));
        }

        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken = authorization.getToken(OAuth2RefreshToken.class);
        setTokenValues(refreshToken,
                entity::setRefreshTokenValue,
                entity::setRefreshTokenIssuedAt,
                entity::setRefreshTokenExpiresAt,
                entity::setRefreshTokenMetadata
        );

        OAuth2Authorization.Token<OidcIdToken> oidcIdToken = authorization.getToken(OidcIdToken.class);
        setTokenValues(oidcIdToken,
                entity::setOidcIdTokenValue,
                entity::setOidcIdTokenIssuedAt,
                entity::setOidcIdTokenExpiresAt,
                entity::setOidcIdTokenMetadata
        );

        if (oidcIdToken != null) {
            entity.setOidcIdTokenClaims(OAuth2Utils.writeMap(oidcIdToken.getClaims()));
        }
        return entity;
    }

    private void setTokenValues(OAuth2Authorization.Token<?> token,
                                Consumer<String> tokenValueConsumer,
                                Consumer<LocalDateTime> issuedAtConsumer,
                                Consumer<LocalDateTime> expiresAtConsumer,
                                Consumer<String> metadataConsumer) {
        if (token != null) {
            OAuth2Token oAuth2Token = token.getToken();
            tokenValueConsumer.accept(oAuth2Token.getTokenValue());
            issuedAtConsumer.accept(LocalDateTime.ofInstant(Objects.requireNonNull(oAuth2Token.getIssuedAt()), ZoneOffset.UTC));
            expiresAtConsumer.accept(LocalDateTime.ofInstant(Objects.requireNonNull(oAuth2Token.getExpiresAt()), ZoneOffset.UTC));
            metadataConsumer.accept(OAuth2Utils.writeMap(token.getMetadata()));
        }
    }

}
