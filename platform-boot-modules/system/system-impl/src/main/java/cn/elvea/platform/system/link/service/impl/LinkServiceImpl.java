package cn.elvea.platform.system.link.service.impl;

import cn.elvea.platform.system.link.model.dto.LinkDto;
import cn.elvea.platform.system.link.service.LinkService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
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
