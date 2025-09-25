package cc.wdev.platform.commons.core.tenant;

/**
 * @author elvea
 */
public class GlobalTenantManager {

    private static volatile TenantConfig config = TenantConfig.builder().build();

    public static TenantConfig getConfig() {
        return GlobalTenantManager.config;
    }

    public static void init(TenantConfig config) {
        GlobalTenantManager.config = config;
    }

}
