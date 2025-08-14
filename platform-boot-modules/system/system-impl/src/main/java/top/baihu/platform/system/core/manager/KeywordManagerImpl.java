package top.baihu.platform.system.core.manager;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.baihu.platform.system.core.domain.entity.KeywordEntity;
import top.baihu.platform.system.core.service.KeywordService;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class KeywordManagerImpl implements KeywordManager {

    private final KeywordService keywordService;

    private final top.baihu.platform.commons.extensions.keyword.KeywordManager keywordManager;

    /**
     * @see KeywordManager#initialize()
     */
    @Override
    public void initialize() {
        keywordManager.initialize(keywordService.findAll().stream().map(KeywordEntity::getContent).toList());
    }

}
