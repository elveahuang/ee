package top.baihu.platform.system.core.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import top.baihu.platform.system.core.domain.dto.LinkDto;
import top.baihu.platform.system.core.service.LinkService;

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
