package cc.elvea.platform.system.commons.api.impl;

import cc.elvea.platform.commons.extensions.keyword.KeywordManager;
import cc.elvea.platform.system.commons.api.KeywordApi;
import cc.elvea.platform.system.keyword.model.entity.KeywordEntity;
import cc.elvea.platform.system.keyword.service.KeywordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class KeywordApiImpl implements KeywordApi {

    private final KeywordService keywordService;

    private final KeywordManager keywordManager;

    /**
     * @see KeywordApi#initialize()
     */
    @Override
    public void initialize() {
        keywordManager.initialize(keywordService.findAll().stream().map(KeywordEntity::getContent).toList());
    }

}
