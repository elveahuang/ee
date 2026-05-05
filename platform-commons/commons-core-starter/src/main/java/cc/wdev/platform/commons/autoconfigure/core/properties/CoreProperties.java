package cc.wdev.platform.commons.autoconfigure.core.properties;

import cc.wdev.platform.commons.core.Context;
import cc.wdev.platform.commons.core.tenant.TenantConfig;
import cc.wdev.platform.commons.message.rabbit.RabbitConfig;
import lombok.Data;
import org.mapstruct.MappingConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@ConfigurationProperties(CoreProperties.PREFIX)
public class CoreProperties implements Serializable {

    public static final String PREFIX = "platform";

    public static final String TENANCY_PREFIX = PREFIX + ".tenancy";

    /**
     * 基本信息
     */
    @NestedConfigurationProperty
    private Context.App app = Context.App.builder().build();

    /**
     * 调试模式
     */
    @NestedConfigurationProperty
    private Context.Debug debug = Context.Debug.builder().build();

    /**
     * 主页配置
     */
    @NestedConfigurationProperty
    private Context.Home home = Context.Home.builder().build();

    /**
     * MapStruct
     */
    @NestedConfigurationProperty
    private Context.MapStruct mapStruct = Context.MapStruct.builder()
        .componentModel(MappingConstants.ComponentModel.DEFAULT)
        .build();

    /**
     * 消息队列
     */
    @NestedConfigurationProperty
    private RabbitConfig rabbit = RabbitConfig.builder().build();

    /**
     * 多租户配置
     */
    @NestedConfigurationProperty
    private TenantConfig tenancy = TenantConfig.builder().build();

}
