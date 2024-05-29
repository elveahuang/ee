package cc.elvea.platform.system.link.provider;

import cc.elvea.platform.system.link.model.dto.LinkDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author elvea
 * @since 24.1.0
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
