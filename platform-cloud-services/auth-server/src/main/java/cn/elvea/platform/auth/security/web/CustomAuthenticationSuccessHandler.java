package cn.elvea.platform.auth.security.web;

import cn.elvea.platform.commons.core.utils.SecurityUtils;
import cn.elvea.platform.system.core.api.UserSessionApi;
import cn.elvea.platform.system.core.helper.UserSessionHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author elvea
 * @since 2.0.0
 */
@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserSessionApi userSessionApi;

    public CustomAuthenticationSuccessHandler(UserSessionApi userSessionApi) {
        this.userSessionApi = userSessionApi;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("onAuthenticationSuccess");

        Long id = SecurityUtils.getUserId(authentication);
        String username = SecurityUtils.getUserName(authentication);

        // 保持用户成功登录记录
        for (int i = 0; i < 1; i++) {
            try {
                this.userSessionApi.saveUserSession(UserSessionHelper.userSession(id, username, true, request));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
