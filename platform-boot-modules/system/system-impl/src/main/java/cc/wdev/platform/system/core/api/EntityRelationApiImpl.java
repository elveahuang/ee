package cc.wdev.platform.system.core.api;

import cc.wdev.platform.system.core.domain.dto.CatalogRelationSaveDto;
import cc.wdev.platform.system.core.service.EntityRelationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class EntityRelationApiImpl implements EntityRelationApi {

    private final EntityRelationService entityRelationService;

    @Override
    public void saveEntityRelation(CatalogRelationSaveDto saveDto) {
    }

}
