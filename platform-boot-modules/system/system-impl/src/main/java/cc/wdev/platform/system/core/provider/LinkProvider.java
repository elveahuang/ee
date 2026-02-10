package cc.wdev.platform.system.core.provider;

import cc.wdev.platform.system.core.domain.dto.LinkDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author elvea
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
