package cc.elvea.platform.security.web.authentication;

import cc.elvea.platform.commons.core.security.CustomAuthorizationGrantType;
import cc.elvea.platform.commons.core.security.CustomParameterNames;
import cc.elvea.platform.commons.enums.CaptchaTypeEnum;
import cc.elvea.platform.commons.exception.InvalidCaptchaException;
import cc.elvea.platform.commons.extensions.captcha.request.CaptchaCheckRequest;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.commons.api.CaptchaApi;
import cc.elvea.platform.system.config.api.ConfigApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static cc.elvea.platform.commons.constants.SecurityConstants.OAUTH_TOKEN_ENDPOINT;
import static cc.elvea.platform.system.commons.constants.SystemConfigConstants.LOGIN_CAPTCHA_ENABLED;

/**
 * @author elvea
 */
@Slf4j
public class CaptchaAuthenticationFilter extends OncePerRequestFilter {

    private final RequestMatcher requestMatcher;

    private final ConfigApi configApi;

    private final CaptchaApi captchaApi;

    private final AuthenticationFailureHandler failureHandler;

    public CaptchaAuthenticationFilter(
            ConfigApi configApi,
            CaptchaApi captchaApi,
            AuthenticationFailureHandler failureHandler
    ) {
        this.configApi = configApi;
        this.captchaApi = captchaApi;
        this.failureHandler = failureHandler;
        requestMatcher = new AntPathRequestMatcher(OAUTH_TOKEN_ENDPOINT);
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {

        // 检查系统是否启用验证码
        if (!this.configApi.getBoolean(LOGIN_CAPTCHA_ENABLED, false)) {
            log.info("Captcha is disabled.");
            chain.doFilter(request, response);
            return;
        }

        // 检查当前请求是否需要校验验证码
        if (!this.requestMatcher.matches(request)) {
            log.info("Captcha Filter not match.");
            chain.doFilter(request, response);
            return;
        }

        log.info("Captcha Filter check start.");
        // 密码模式才需要做验证码验证
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (CustomAuthorizationGrantType.PASSWORD.getValue().equalsIgnoreCase(grantType)) {
            String captchaKey = request.getParameter(CustomParameterNames.CAPTCHA_KEY);
            String captchaValue = request.getParameter(CustomParameterNames.CAPTCHA_VALUE);

            try {
                if (StringUtils.isEmpty(captchaKey) || StringUtils.isEmpty(captchaValue)) {
                    throw new InvalidCaptchaException("Invalid Captcha.");
                }

                CaptchaCheckRequest captchaCheckRequest = CaptchaCheckRequest.builder()
                        .type(CaptchaTypeEnum.CODE)
                        .key(captchaKey)
                        .value(captchaValue)
                        .build();
                if (!this.captchaApi.check(captchaCheckRequest)) {
                    throw new InvalidCaptchaException("Invalid Captcha.");
                }
            } catch (AuthenticationException ex) {
                this.failureHandler.onAuthenticationFailure(request, response, ex);
                return;
            }
        }
        chain.doFilter(request, response);
    }

}
