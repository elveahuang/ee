package top.baihu.platform.security.web.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import top.baihu.platform.commons.enums.CaptchaTypeEnum;
import top.baihu.platform.commons.exception.InvalidCaptchaException;
import top.baihu.platform.commons.extensions.captcha.request.CaptchaCheckRequest;
import top.baihu.platform.commons.security.CustomAuthorizationGrantType;
import top.baihu.platform.commons.security.CustomParameterNames;
import top.baihu.platform.commons.utils.StringUtils;
import top.baihu.platform.system.core.manager.CaptchaManager;
import top.baihu.platform.system.core.manager.ConfigManager;

import java.io.IOException;

import static top.baihu.platform.commons.constants.SecurityConstants.OAUTH_TOKEN_ENDPOINT;
import static top.baihu.platform.system.commons.constants.SystemConfigConstants.LOGIN_CAPTCHA_ENABLED;

/**
 * @author elvea
 */
@Slf4j
public class CaptchaAuthenticationFilter extends OncePerRequestFilter {

    private final RequestMatcher requestMatcher;

    private final ConfigManager configManager;

    private final CaptchaManager captchaManager;

    private final AuthenticationFailureHandler failureHandler;

    public CaptchaAuthenticationFilter(
        ConfigManager configManager,
        CaptchaManager captchaManager,
        AuthenticationFailureHandler failureHandler
    ) {
        this.configManager = configManager;
        this.captchaManager = captchaManager;
        this.failureHandler = failureHandler;
        requestMatcher = PathPatternRequestMatcher.withDefaults().matcher(OAUTH_TOKEN_ENDPOINT);
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {

        // 检查系统是否启用验证码
        if (!this.configManager.getBoolean(LOGIN_CAPTCHA_ENABLED, false)) {
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
                if (!this.captchaManager.check(captchaCheckRequest)) {
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
