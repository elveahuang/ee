package cc.elvea.platform.auth.security.authentication;

import cc.elvea.platform.auth.security.utils.OAuth2EndpointUtils;
import cc.elvea.platform.commons.core.security.CustomAuthorizationGrantType;
import cc.elvea.platform.commons.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.delimitedListToStringArray;

/**
 * @author elvea
 */
public class PasswordAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!CustomAuthorizationGrantType.PASSWORD.getValue().equals(grantType)) {
            return null;
        }

        // 客户端信息
        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
        if (clientPrincipal == null) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);

        // scope (OPTIONAL)
        String scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
        if (StringUtils.isNotEmpty(scope) && parameters.get(OAuth2ParameterNames.SCOPE).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }
        Set<String> requestedScopes = null;
        if (StringUtils.isNotEmpty(scope)) {
            requestedScopes = new HashSet<>(Arrays.asList(delimitedListToStringArray(scope, " ")));
        }

        // 检查用户名
        String username = parameters.getFirst(OAuth2ParameterNames.USERNAME);
        if (!StringUtils.isNotEmpty(username) || parameters.get(OAuth2ParameterNames.USERNAME).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        // 检查密码
        String password = parameters.getFirst(OAuth2ParameterNames.PASSWORD);
        if (!StringUtils.isNotEmpty(password) || parameters.get(OAuth2ParameterNames.PASSWORD).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        // 附加参数
        Map<String, Object> additionalParameters = parameters.entrySet().stream()
                .filter(e -> !e.getKey().equals(OAuth2ParameterNames.GRANT_TYPE) && !e.getKey().equals(OAuth2ParameterNames.SCOPE))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get(0)));

        return new PasswordAuthenticationToken(clientPrincipal, requestedScopes, additionalParameters);
    }

}
