package top.baihu.platform.auth.security.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.commons.enums.ResponseCodeEnum;
import top.baihu.platform.commons.utils.ServletUtils;

/**
 * @author elvea
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.error("CustomAuthenticationEntryPoint", e);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletUtils.renderJson(response, R.fail(ResponseCodeEnum.UNAUTHORIZED));
    }

}
