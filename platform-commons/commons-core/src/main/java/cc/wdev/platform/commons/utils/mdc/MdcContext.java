package cc.wdev.platform.commons.utils.mdc;

import cc.wdev.platform.commons.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.slf4j.MDC;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.util.Map;

import static cc.wdev.platform.commons.constants.GlobalConstants.REQUEST_ID_KEY;

/**
 * @author elvea
 */
@Slf4j
public abstract class MdcContext {

    private final static String REQUEST_ID = "requestId";

    public static void handleAspect() {
        if (StringUtils.isEmpty(getRequestId())) {
            MdcContext.setRequestId(StringUtils.uuid());
        }
    }

    public static void handleServletRequest(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        log.info("[MdcContext] handleServletRequest start");

        String requestId = request.getHeader(REQUEST_ID_KEY);
        if (StringUtils.isEmpty(requestId)) {
            requestId = StringUtils.uuid();
            log.info("[MdcContext] generate servlet requestId [{}]", requestId);
        }
        MdcContext.setRequestId(requestId);

        if (!response.getHeaderNames().contains(REQUEST_ID_KEY)) {
            log.info("[MdcContext] set requestId [{}]", requestId);
            response.setHeader(REQUEST_ID_KEY, requestId);
        }
    }

    public static void handleReactiveRequest(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        log.info("[MdcContext] handleReactiveRequest start");

        String requestId = request.getHeaders().getFirst(REQUEST_ID_KEY);
        if (StringUtils.isEmpty(requestId)) {
            requestId = StringUtils.uuid();
            log.info("[MdcContext] generate reactive requestId [{}]", requestId);
        }
        MdcContext.setRequestId(requestId);
    }

    public static void setAsyncContext(Map<String, String> context) {
        if (context == null) {
            MdcContext.clear();
        } else {
            MDC.setContextMap(context);
        }
        if (StringUtils.isEmpty(getRequestId())) {
            MdcContext.setRequestId(StringUtils.uuid());
        }
    }

    public static void setRequestId(String requestId) {
        MDC.put(REQUEST_ID, requestId);
    }

    public static String getRequestId() {
        return MDC.get(REQUEST_ID);
    }

    public static void clear() {
        log.info("[MdcContext] clear context");
        MDC.clear();
    }

}
