package cn.elvea.platform.auth.security.web;

import cn.elvea.platform.commons.core.utils.ServletUtils;
import cn.elvea.platform.commons.core.web.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 * @since 0.0.1
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, AccessDeniedException accessDeniedException) {
        ServletUtils.renderJson(servletResponse, R.error());
    }

}
