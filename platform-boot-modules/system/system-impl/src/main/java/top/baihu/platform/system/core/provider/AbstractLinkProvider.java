package top.baihu.platform.system.core.provider;

import jakarta.servlet.http.HttpServletRequest;
import top.baihu.platform.system.core.domain.dto.LinkDto;

/**
 * @author elvea
 */
public abstract class AbstractLinkProvider implements LinkProvider {

    @Override
    public LinkDto generate(HttpServletRequest request) {
        return null;
    }

    @Override
    public LinkDto process(String key) {
        return null;
    }

}
