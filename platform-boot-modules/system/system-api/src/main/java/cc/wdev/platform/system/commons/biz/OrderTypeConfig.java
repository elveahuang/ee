package cc.wdev.platform.system.commons.biz;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单类型配置
 *
 * @author elvea
 */
@Data
@Builder
public class OrderTypeConfig implements Serializable {
    private String callback;
}
