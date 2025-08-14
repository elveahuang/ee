package top.baihu.platform.auth.security.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.baihu.platform.commons.utils.SecurityUtils;
import top.baihu.platform.system.core.api.UserSessionApi;
import top.baihu.platform.system.core.helper.UserSessionHelper;

import java.io.IOException;

/**
 * @author elvea
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

        Long id = SecurityUtils.getUid(authentication);
        String username = SecurityUtils.getUsername(authentication);

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
