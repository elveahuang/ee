package cc.wdev.platform.commons.utils;

import org.slf4j.MDC;

/**
 * @author elvea
 */
public abstract class MDCUtils {

    private static final String REQUEST_ID_KEY = "requestId";

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
