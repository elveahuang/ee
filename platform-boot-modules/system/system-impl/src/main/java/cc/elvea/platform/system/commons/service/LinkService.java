package cc.elvea.platform.system.commons.service;

import cc.elvea.platform.system.commons.model.dto.LinkDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author elvea
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
