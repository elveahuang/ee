package cc.wdev.platform.system.commons.biz;

import cc.wdev.platform.commons.enums.BaseBizTypeConfig;
import lombok.Builder;
import lombok.Data;

/**
 * 订单类型配置
 *
 * @author elvea
 */
@Data
@Builder
public class OrderTypeConfig implements BaseBizTypeConfig {
    private String callback;
}
