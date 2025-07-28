package cc.elvea.platform.system.core.provider;

import cc.elvea.platform.system.core.domain.dto.LinkDto;
import jakarta.servlet.http.HttpServletRequest;

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
