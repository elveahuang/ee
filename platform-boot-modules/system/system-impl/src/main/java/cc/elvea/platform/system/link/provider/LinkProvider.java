package cc.elvea.platform.system.link.provider;

import cc.elvea.platform.system.link.model.dto.LinkDto;
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
