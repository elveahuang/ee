package top.baihu.platform.system.core.provider;

import jakarta.servlet.http.HttpServletRequest;
import top.baihu.platform.system.core.domain.dto.LinkDto;

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
