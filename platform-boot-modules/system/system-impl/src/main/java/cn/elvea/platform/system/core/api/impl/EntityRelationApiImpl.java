package cn.elvea.platform.system.core.api.impl;

import cn.elvea.platform.system.catalog.model.dto.CatalogRelationSaveDto;
import cn.elvea.platform.system.core.api.EntityRelationApi;
import cn.elvea.platform.system.core.service.EntityRelationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Service
@AllArgsConstructor
public class EntityRelationApiImpl implements EntityRelationApi {

    private final EntityRelationService entityRelationService;

    @Override
    public void saveEntityRelation(CatalogRelationSaveDto saveDto) {
    }

}
