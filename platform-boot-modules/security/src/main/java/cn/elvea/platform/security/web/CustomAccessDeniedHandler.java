package cn.elvea.platform.security.web;

import cn.elvea.platform.commons.core.enums.ResponseCodeEnum;
import cn.elvea.platform.commons.core.utils.ServletUtils;
import cn.elvea.platform.commons.core.web.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        log.error("CustomAccessDeniedHandler.", e);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ServletUtils.renderJson(response, R.fail(ResponseCodeEnum.FORBIDDEN));
    }

}
