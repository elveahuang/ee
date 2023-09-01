package cn.elvea.platform.system.link.provider;

import cn.elvea.platform.system.link.model.dto.LinkDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface LinkProvider {

    /**
     * 生成短链接
     */
    LinkDto generate(HttpServletRequest request) throws Exception;

    /**
     * 解析短链接
     */
    LinkDto process(String key) throws Exception;

}
