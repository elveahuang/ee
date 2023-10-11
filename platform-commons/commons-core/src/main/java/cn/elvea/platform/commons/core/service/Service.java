package cn.elvea.platform.commons.core.service;

import java.time.LocalDateTime;

/**
 * 通用服务接口
 *
 * @author elvea
 * @since 0.0.1
 */
public interface Service {

    /**
     * 获取当前系统时间
     */
    default LocalDateTime getCurLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     *
     */
    Long generateId();

    /**
     *
     */
    String generateIdAsString();

    /**
     *
     */
    String generateCode(String prefix);

}
