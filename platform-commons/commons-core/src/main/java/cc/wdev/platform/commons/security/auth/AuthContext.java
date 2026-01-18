package cc.wdev.platform.commons.security.auth;

import jakarta.servlet.ServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public class AuthContext {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void handleServletRequest(ServletRequest request) {
        log.info("[AuthContext] handleServletRequest start");
        String type = request.getParameter("type");
        setIdentityType(type);
    }

    public static void setIdentityType(String type) {
        threadLocal.set(type);
    }

    public static String getIdentityType() {
        if (threadLocal.get() == null) {
            return "user";
        }
        return threadLocal.get();
    }

    public static void clear() {
        log.info("[AuthContext] clear context");
        threadLocal.remove();
    }

}
