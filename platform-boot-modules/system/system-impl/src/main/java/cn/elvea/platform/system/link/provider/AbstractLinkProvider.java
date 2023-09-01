package cn.elvea.platform.system.link.provider;

import cn.elvea.platform.system.link.model.dto.LinkDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author elvea
 * @since 0.0.1
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
