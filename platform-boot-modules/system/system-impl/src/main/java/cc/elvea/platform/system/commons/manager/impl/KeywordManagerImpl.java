package cc.elvea.platform.system.commons.manager.impl;

import cc.elvea.platform.system.commons.manager.KeywordManager;
import cc.elvea.platform.system.keyword.model.entity.KeywordEntity;
import cc.elvea.platform.system.keyword.service.KeywordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class KeywordManagerImpl implements KeywordManager {

    private final KeywordService keywordService;

    private final cc.elvea.platform.commons.extensions.keyword.KeywordManager keywordManager;

    /**
     * @see KeywordManager#initialize()
     */
    @Override
    public void initialize() {
        keywordManager.initialize(keywordService.findAll().stream().map(KeywordEntity::getContent).toList());
    }

}
