package cc.elvea.platform.system.commons.service.impl;

import cc.elvea.platform.system.commons.model.dto.LinkDto;
import cc.elvea.platform.system.commons.service.LinkService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Override
    public LinkDto generate(HttpServletRequest request) {
        return null;
    }

    @Override
    public String process(HttpServletRequest request) {
        return "";
    }

}
