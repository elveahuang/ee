package top.baihu.platform.commons.oapis.lark;

import top.baihu.platform.commons.oapis.lark.cache.LocalCache;
import top.baihu.platform.commons.oapis.lark.token.TicketManager;

/**
 * @author elvea
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
