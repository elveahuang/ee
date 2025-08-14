package top.baihu.platform.auth.security.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.commons.enums.ResponseCodeEnum;
import top.baihu.platform.commons.exception.InvalidCaptchaException;
import top.baihu.platform.commons.utils.ServletUtils;

/**
 * @author elvea
 */
@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.info("CustomAuthenticationFailureHandler.onAuthenticationFailure");

        if (e instanceof OAuth2AuthenticationException) {
            OAuth2Error error = ((OAuth2AuthenticationException) e).getError();
            ServletUtils.renderJson(response, R.fail(ResponseCodeEnum.BAD_REQUEST.getCode(), error.getDescription()));
        } else {
            if (e instanceof InvalidCaptchaException) {
                ServletUtils.renderJson(response, R.fail(ResponseCodeEnum.INVALID_CAPTCHA));
            } else {
                ServletUtils.renderJson(response, R.error(ResponseCodeEnum.ERROR));
            }
        }
    }

}
