package cn.elvea.platform.commons.core.service;

/**
 * 通用服务接口
 *
 * @author elvea
 * @since 0.0.1
 */
public interface Service {

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
