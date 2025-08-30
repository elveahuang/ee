package cc.wdev.platform.commons.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;

import java.util.Map;

import static cc.wdev.platform.commons.constants.GlobalConstants.REQUEST_ID_KEY;

/**
 * @author elvea
 */
public abstract class MDCUtils {

    public static void handleRequest(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        String requestId = request.getHeader(REQUEST_ID_KEY);
        if (StringUtils.isEmpty(requestId)) {
            requestId = StringUtils.uuid();
        }
        MDCUtils.setRequestId(requestId);
        response.addHeader(REQUEST_ID_KEY, requestId);
    }

    public static void setAsyncContext(Map<String, String> context) {
        if (context == null) {
            MDCUtils.clear();
        } else {
            MDC.setContextMap(context);
        }
        if (MDCUtils.getRequestId() == null) {
            MDCUtils.setRequestId();
        }
    }

    public static void setRequestId() {
        MDC.put(REQUEST_ID_KEY, StringUtils.uuid());
    }

    public static void setRequestId(String requestId) {
        MDC.put(REQUEST_ID_KEY, requestId);
    }

    public static String getRequestId() {
        return MDC.get(REQUEST_ID_KEY);
    }

    public static void clear() {
        MDC.clear();
    }

}
