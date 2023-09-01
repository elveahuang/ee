package cn.elvea.platform.commons.core.oapis.lark;

import cn.elvea.platform.commons.core.oapis.lark.cache.LocalCache;
import cn.elvea.platform.commons.core.oapis.lark.token.TicketManager;

/**
 * @author elvea
 * @since 0.0.1
 */
public class GlobalTicketManager {

    private static volatile TicketManager globalTicketManager = new TicketManager(LocalCache.getInstance());

    public static TicketManager getTicketManager() {
        return globalTicketManager;
    }

    public static void setTicketManager(TicketManager jsapiTicketManager) {
        globalTicketManager = jsapiTicketManager;
    }

}
