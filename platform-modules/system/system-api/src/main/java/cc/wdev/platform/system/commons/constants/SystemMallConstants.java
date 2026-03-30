package cc.wdev.platform.system.commons.constants;

import cc.wdev.platform.system.commons.biz.OrderTypeConfig;

/**
 * @author elvea
 */
public interface SystemMallConstants {

    OrderTypeConfig DEFAULT_CONFIG = OrderTypeConfig.builder()
        .callback("")
        .build();

}
