package cc.elvea.platform.security.web;

import cc.elvea.platform.commons.enums.ResponseCodeEnum;
import cc.elvea.platform.commons.utils.ServletUtils;
import cc.elvea.platform.commons.web.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 * @since 24.1.0
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
