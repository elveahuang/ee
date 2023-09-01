package cn.elvea.platform.system.core.enums;

import lombok.Getter;

/**
 * 角色类型
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum RoleTypeEnum {
    /**
     * 系统管理员
     */
    SYSTEM_ADMINISTRATOR,
    /**
     * 管理员
     */
    ADMINISTRATOR,
    /**
     * 用户
     */
    USER,
    /**
     * 匿名用户
     */
    ANONYMOUS_USER;
}
