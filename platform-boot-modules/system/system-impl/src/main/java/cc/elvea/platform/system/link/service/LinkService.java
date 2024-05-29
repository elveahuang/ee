package cc.elvea.platform.system.link.service;

import cc.elvea.platform.system.link.model.dto.LinkDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface LinkService {

    /**
     * 生成短链接
     */
    LinkDto generate(HttpServletRequest request);

    /**
     * 解析短链接，并返回当前目标环境/客户端链接
     */
    String process(HttpServletRequest request);

}
