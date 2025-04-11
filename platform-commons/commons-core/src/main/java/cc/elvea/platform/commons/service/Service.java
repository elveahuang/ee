package cc.elvea.platform.commons.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 通用服务接口
 *
 * @author elvea
 */
public interface Service {

    /**
     * 获取当前系统时间
     */
    default LocalDateTime getCurLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前系统时间
     */
    default LocalDate getCurLocalDate() {
        return LocalDate.now();
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
