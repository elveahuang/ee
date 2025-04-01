package cc.elvea.platform.auth.security.utils;

import cc.elvea.platform.commons.core.security.jackson.LongMixin;
import cc.elvea.platform.commons.core.security.jackson.UserMixin;
import cc.elvea.platform.commons.core.security.user.User;
import cc.elvea.platform.system.security.model.dto.ClientDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @author elvea
 */
public abstract class OAuth2Utils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        ClassLoader classLoader = OAuth2Utils.class.getClassLoader();
        objectMapper.registerModules(new CoreJackson2Module());
        objectMapper.registerModules(SecurityJackson2Modules.getModules(classLoader));
        objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
        //
        objectMapper.addMixIn(Long.class, LongMixin.class);
        objectMapper.addMixIn(User.class, UserMixin.class);
    }

    public static ClientDto toRegisteredClientDto(RegisteredClient client) {
        List<String> clientAuthenticationMethods = new ArrayList<>(client.getClientAuthenticationMethods().size());
        client.getClientAuthenticationMethods().forEach(clientAuthenticationMethod ->
                clientAuthenticationMethods.add(clientAuthenticationMethod.getValue())
        );
        List<String> authorizationGrantTypes = new ArrayList<>(client.getAuthorizationGrantTypes().size());
        client.getAuthorizationGrantTypes().forEach(authorizationGrantType ->
                authorizationGrantTypes.add(authorizationGrantType.getValue())
        );
        ClientDto entity = new ClientDto();
        entity.setId(Long.valueOf(client.getId()));
        entity.setClientId(client.getClientId());
        entity.setClientName(client.getClientName());
        entity.setClientSecret(client.getClientSecret());
        entity.setClientIdIssuedAt(LocalDateTime.ofInstant(Objects.requireNonNull(client.getClientIdIssuedAt()), ZoneOffset.UTC));
        entity.setClientSecretExpiresAt(LocalDateTime.ofInstant(Objects.requireNonNull(client.getClientSecretExpiresAt()), ZoneOffset.UTC));
        entity.setClientAuthenticationMethods(StringUtils.collectionToCommaDelimitedString(clientAuthenticationMethods));
        entity.setAuthorizationGrantTypes(StringUtils.collectionToCommaDelimitedString(authorizationGrantTypes));
        entity.setRedirectUris(StringUtils.collectionToCommaDelimitedString(client.getRedirectUris()));
        entity.setPostLogoutRedirectUris(StringUtils.collectionToCommaDelimitedString(client.getPostLogoutRedirectUris()));
        entity.setScopes(StringUtils.collectionToCommaDelimitedString(client.getScopes()));
        entity.setClientSettings(writeMap(client.getClientSettings().getSettings()));
        entity.setTokenSettings(writeMap(client.getTokenSettings().getSettings()));
        return entity;
    }

    public static RegisteredClient toRegisteredClient(ClientDto client) {
        Set<String> clientAuthenticationMethods = StringUtils.commaDelimitedListToSet(client.getClientAuthenticationMethods());
        Set<String> authorizationGrantTypes = StringUtils.commaDelimitedListToSet(client.getAuthorizationGrantTypes());
        Set<String> redirectUris = StringUtils.commaDelimitedListToSet(client.getRedirectUris());
        Set<String> clientScopes = StringUtils.commaDelimitedListToSet(client.getScopes());

        RegisteredClient.Builder builder = RegisteredClient.withId(String.valueOf(client.getClientId()))
                .clientId(client.getClientId())
                .clientName(client.getClientName())
                .clientSecret(client.getClientSecret())
                .clientAuthenticationMethods(authenticationMethods ->
                        clientAuthenticationMethods.forEach(authenticationMethod ->
                                authenticationMethods.add(
                                        OAuth2Utils.resolveClientAuthenticationMethod(authenticationMethod)
                                )
                        )
                )
                .authorizationGrantTypes((grantTypes) ->
                        authorizationGrantTypes.forEach(grantType ->
                                grantTypes.add(
                                        OAuth2Utils.resolveAuthorizationGrantType(grantType)
                                )
                        )
                )
                .redirectUris((uris) -> uris.addAll(redirectUris))
                .scopes((scopes) -> scopes.addAll(clientScopes));
        if (client.getClientIdIssuedAt() != null) {
            builder.clientIdIssuedAt(client.getClientIdIssuedAt().toInstant(ZoneOffset.UTC));
        }
        if (client.getClientSecretExpiresAt() != null) {
            builder.clientSecretExpiresAt(client.getClientSecretExpiresAt().toInstant(ZoneOffset.UTC));
        }
        return builder.build();
    }

    public static String writeMap(Map<String, Object> metadata) {
        try {
            return objectMapper.writeValueAsString(metadata);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    public static Map<String, Object> parseMap(String data) {
        try {
            return objectMapper.readValue(data, new TypeReference<>() {
            });
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    public static AuthorizationGrantType resolveAuthorizationGrantType(String authorizationGrantType) {
        if (AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.AUTHORIZATION_CODE;
        } else if (AuthorizationGrantType.CLIENT_CREDENTIALS.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.CLIENT_CREDENTIALS;
        } else if (AuthorizationGrantType.REFRESH_TOKEN.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.REFRESH_TOKEN;
        } else if (AuthorizationGrantType.DEVICE_CODE.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.DEVICE_CODE;
        }
        return new AuthorizationGrantType(authorizationGrantType);
    }

    public static ClientAuthenticationMethod resolveClientAuthenticationMethod(String clientAuthenticationMethod) {
        if (ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue().equals(clientAuthenticationMethod)) {
            return ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
        } else if (ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue().equals(clientAuthenticationMethod)) {
            return ClientAuthenticationMethod.CLIENT_SECRET_POST;
        } else if (ClientAuthenticationMethod.NONE.getValue().equals(clientAuthenticationMethod)) {
            return ClientAuthenticationMethod.NONE;
        }
        return new ClientAuthenticationMethod(clientAuthenticationMethod);
    }

}
