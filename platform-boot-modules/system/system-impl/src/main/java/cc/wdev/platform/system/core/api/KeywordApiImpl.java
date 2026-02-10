package cc.wdev.platform.system.core.api;

import cc.wdev.platform.system.core.domain.entity.KeywordEntity;
import cc.wdev.platform.system.core.service.KeywordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class KeywordApiImpl implements KeywordApi {

    private final KeywordService keywordService;

    private final cc.wdev.platform.commons.extensions.keyword.KeywordManager keywordManager;

    /**
     * @see KeywordApi#initialize()
     */
    @Override
    public void initialize() {
        keywordManager.initialize(keywordService.findAll().stream().map(KeywordEntity::getContent).toList());
    }

}
