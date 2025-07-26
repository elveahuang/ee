package cc.elvea.platform.system.commons.provider;

import cc.elvea.platform.system.commons.model.dto.LinkDto;
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
