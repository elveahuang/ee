package cc.elvea.platform.security.authentication;

import cc.elvea.platform.commons.core.security.CustomAuthorizationGrantType;
import cc.elvea.platform.commons.core.security.CustomParameterNames;
import cc.elvea.platform.security.utils.OAuth2EndpointUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author elvea
 */
public class SmsAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!CustomAuthorizationGrantType.SMS.getValue().equals(grantType)) {
            return null;
        }

        // client (REQUIRED)
        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
        if (clientPrincipal == null) {
            OAuth2EndpointUtils.throwError(
                    OAuth2ErrorCodes.INVALID_REQUEST,
                    OAuth2ErrorCodes.INVALID_CLIENT,
                    OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }

        MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);

        // mobile (REQUIRED)
        OAuth2EndpointUtils.checkRequiredParameter(parameters, CustomParameterNames.MOBILE);

        // code (REQUIRED)
        OAuth2EndpointUtils.checkRequiredParameter(parameters, CustomParameterNames.CODE);

        // scope (OPTIONAL)
        String scope = OAuth2EndpointUtils.checkOptionalParameter(parameters, OAuth2ParameterNames.SCOPE);

        Set<String> requestedScopes = null;
        if (StringUtils.hasText(scope)) {
            requestedScopes = new HashSet<>(Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
        }

        Map<String, Object> additionalParameters = new HashMap<>();
        parameters.forEach((key, value) -> {
            if (!key.equals(OAuth2ParameterNames.GRANT_TYPE) && !key.equals(OAuth2ParameterNames.REFRESH_TOKEN) && !key.equals(OAuth2ParameterNames.SCOPE)) {
                additionalParameters.put(key, value.get(0));
            }
        });

        return new SmsAuthenticationToken(clientPrincipal, requestedScopes, additionalParameters);
    }

}
