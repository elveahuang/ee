package top.baihu.platform.security.utils;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author elvea
 */
public final class OAuth2ConfigUtils {

    private OAuth2ConfigUtils() {
    }

    public static RegisteredClientRepository getRegisteredClientRepository(HttpSecurity httpSecurity) {
        RegisteredClientRepository registeredClientRepository = httpSecurity.getSharedObject(RegisteredClientRepository.class);
        if (registeredClientRepository == null) {
            registeredClientRepository = getBean(httpSecurity, RegisteredClientRepository.class);
            httpSecurity.setSharedObject(RegisteredClientRepository.class, registeredClientRepository);
        }
        return registeredClientRepository;
    }

    public static OAuth2AuthorizationService getAuthorizationService(HttpSecurity httpSecurity) {
        OAuth2AuthorizationService authorizationService = httpSecurity.getSharedObject(OAuth2AuthorizationService.class);
        if (authorizationService == null) {
            authorizationService = getOptionalBean(httpSecurity, OAuth2AuthorizationService.class);
            if (authorizationService == null) {
                authorizationService = new InMemoryOAuth2AuthorizationService();
            }
            httpSecurity.setSharedObject(OAuth2AuthorizationService.class, authorizationService);
        }
        return authorizationService;
    }

    public static OAuth2AuthorizationConsentService getAuthorizationConsentService(HttpSecurity httpSecurity) {
        OAuth2AuthorizationConsentService authorizationConsentService = httpSecurity.getSharedObject(OAuth2AuthorizationConsentService.class);
        if (authorizationConsentService == null) {
            authorizationConsentService = getOptionalBean(httpSecurity, OAuth2AuthorizationConsentService.class);
            if (authorizationConsentService == null) {
                authorizationConsentService = new InMemoryOAuth2AuthorizationConsentService();
            }
            httpSecurity.setSharedObject(OAuth2AuthorizationConsentService.class, authorizationConsentService);
        }
        return authorizationConsentService;
    }

    @SuppressWarnings("unchecked")
    public static OAuth2TokenGenerator<? extends OAuth2Token> getTokenGenerator(HttpSecurity httpSecurity) {
        OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = httpSecurity.getSharedObject(OAuth2TokenGenerator.class);
        if (tokenGenerator == null) {
            tokenGenerator = getOptionalBean(httpSecurity, OAuth2TokenGenerator.class);
            if (tokenGenerator == null) {
                JwtGenerator jwtGenerator = getJwtGenerator(httpSecurity);
                OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
                OAuth2TokenCustomizer<OAuth2TokenClaimsContext> accessTokenCustomizer = getAccessTokenCustomizer(httpSecurity);
                if (accessTokenCustomizer != null) {
                    accessTokenGenerator.setAccessTokenCustomizer(accessTokenCustomizer);
                }
                OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
                if (jwtGenerator != null) {
                    tokenGenerator = new DelegatingOAuth2TokenGenerator(
                        jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
                } else {
                    tokenGenerator = new DelegatingOAuth2TokenGenerator(
                        accessTokenGenerator, refreshTokenGenerator);
                }
            }
            httpSecurity.setSharedObject(OAuth2TokenGenerator.class, tokenGenerator);
        }
        return tokenGenerator;
    }

    private static JwtGenerator getJwtGenerator(HttpSecurity httpSecurity) {
        JwtGenerator jwtGenerator = httpSecurity.getSharedObject(JwtGenerator.class);
        if (jwtGenerator == null) {
            JwtEncoder jwtEncoder = getJwtEncoder(httpSecurity);
            if (jwtEncoder != null) {
                jwtGenerator = new JwtGenerator(jwtEncoder);
                OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer = getJwtCustomizer(httpSecurity);
                if (jwtCustomizer != null) {
                    jwtGenerator.setJwtCustomizer(jwtCustomizer);
                }
                httpSecurity.setSharedObject(JwtGenerator.class, jwtGenerator);
            }
        }
        return jwtGenerator;
    }

    private static JwtEncoder getJwtEncoder(HttpSecurity httpSecurity) {
        JwtEncoder jwtEncoder = httpSecurity.getSharedObject(JwtEncoder.class);
        if (jwtEncoder == null) {
            jwtEncoder = getOptionalBean(httpSecurity, JwtEncoder.class);
            if (jwtEncoder == null) {
                JWKSource<SecurityContext> jwkSource = getJwkSource(httpSecurity);
                if (jwkSource != null) {
                    jwtEncoder = new NimbusJwtEncoder(jwkSource);
                }
            }
            if (jwtEncoder != null) {
                httpSecurity.setSharedObject(JwtEncoder.class, jwtEncoder);
            }
        }
        return jwtEncoder;
    }

    @SuppressWarnings("unchecked")
    static JWKSource<SecurityContext> getJwkSource(HttpSecurity httpSecurity) {
        JWKSource<SecurityContext> jwkSource = httpSecurity.getSharedObject(JWKSource.class);
        if (jwkSource == null) {
            ResolvableType type = ResolvableType.forClassWithGenerics(JWKSource.class, SecurityContext.class);
            jwkSource = getOptionalBean(httpSecurity, type);
            if (jwkSource != null) {
                httpSecurity.setSharedObject(JWKSource.class, jwkSource);
            }
        }
        return jwkSource;
    }

    private static OAuth2TokenCustomizer<JwtEncodingContext> getJwtCustomizer(HttpSecurity httpSecurity) {
        ResolvableType type = ResolvableType.forClassWithGenerics(OAuth2TokenCustomizer.class, JwtEncodingContext.class);
        return getOptionalBean(httpSecurity, type);
    }

    private static OAuth2TokenCustomizer<OAuth2TokenClaimsContext> getAccessTokenCustomizer(HttpSecurity httpSecurity) {
        ResolvableType type = ResolvableType.forClassWithGenerics(OAuth2TokenCustomizer.class, OAuth2TokenClaimsContext.class);
        return getOptionalBean(httpSecurity, type);
    }

    static AuthorizationServerSettings getAuthorizationServerSettings(HttpSecurity httpSecurity) {
        AuthorizationServerSettings authorizationServerSettings = httpSecurity.getSharedObject(AuthorizationServerSettings.class);
        if (authorizationServerSettings == null) {
            authorizationServerSettings = getBean(httpSecurity, AuthorizationServerSettings.class);
            httpSecurity.setSharedObject(AuthorizationServerSettings.class, authorizationServerSettings);
        }
        return authorizationServerSettings;
    }

    static <T> T getBean(HttpSecurity httpSecurity, Class<T> type) {
        return httpSecurity.getSharedObject(ApplicationContext.class).getBean(type);
    }

    @SuppressWarnings("unchecked")
    static <T> T getBean(HttpSecurity httpSecurity, ResolvableType type) {
        ApplicationContext context = httpSecurity.getSharedObject(ApplicationContext.class);
        String[] names = context.getBeanNamesForType(type);
        if (names.length == 1) {
            return (T) context.getBean(names[0]);
        }
        if (names.length > 1) {
            throw new NoUniqueBeanDefinitionException(type, names);
        }
        throw new NoSuchBeanDefinitionException(type);
    }

    static <T> T getOptionalBean(HttpSecurity httpSecurity, Class<T> type) {
        Map<String, T> beansMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(
            httpSecurity.getSharedObject(ApplicationContext.class), type);
        if (beansMap.size() > 1) {
            throw new NoUniqueBeanDefinitionException(type, beansMap.size(),
                "Expected single matching bean of type '" + type.getName() + "' but found " +
                    beansMap.size() + ": " + StringUtils.collectionToCommaDelimitedString(beansMap.keySet()));
        }
        return (!beansMap.isEmpty() ? beansMap.values().iterator().next() : null);
    }

    @SuppressWarnings("unchecked")
    static <T> T getOptionalBean(HttpSecurity httpSecurity, ResolvableType type) {
        ApplicationContext context = httpSecurity.getSharedObject(ApplicationContext.class);
        String[] names = context.getBeanNamesForType(type);
        if (names.length > 1) {
            throw new NoUniqueBeanDefinitionException(type, names);
        }
        return names.length == 1 ? (T) context.getBean(names[0]) : null;
    }

}
