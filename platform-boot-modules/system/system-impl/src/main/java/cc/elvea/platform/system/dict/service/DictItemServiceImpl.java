package cc.elvea.platform.system.dict.service;

import cc.elvea.platform.commons.data.jdbc.utils.JdbcUtils;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.dict.model.entity.DictItemEntity;
import cc.elvea.platform.system.dict.model.entity.DictItemEntity_;
import cc.elvea.platform.system.dict.model.entity.DictRelationEntity;
import cc.elvea.platform.system.dict.model.request.DictRelationRequest;
import cc.elvea.platform.system.dict.model.request.DictSearchRequest;
import cc.elvea.platform.system.dict.repository.DictItemRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictItemServiceImpl
        extends BaseCachingEntityService<DictItemEntity, Long, DictItemRepository>
        implements DictItemService {

    private final DictRelationService dictRelationService;

    /**
     * @see DictItemService#search(DictSearchRequest)
     */
    @Override
    public Page<DictItemEntity> search(DictSearchRequest request) {
        Specification<DictItemEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(DictItemEntity_.TYPE_ID), request.getTypeId()));
            if (StringUtils.isNotEmpty(request.getQ())) {
                predicates.add(builder.like(root.get(DictItemEntity_.TITLE), JdbcUtils.generateLike(request.getQ())));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification, request.getPageable());
    }

    /**
     * @see DictItemService#findByTypeId(Long)
     */
    @Override
    public List<DictItemEntity> findByTypeId(Long typeId) {
        Specification<DictItemEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(DictItemEntity_.TYPE_ID), typeId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    /**
     * @see DictItemService#findByTarget(DictRelationRequest)
     */
    @Override
    public List<DictItemEntity> findByTarget(DictRelationRequest request) {
        List<DictRelationEntity> relationList = this.dictRelationService.findRelations(request);

        List<DictItemEntity> dictItemList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(relationList)) {
            dictItemList.addAll(this.findByIds(relationList.stream().map(DictRelationEntity::getItemId).toList()));
        }
        return dictItemList;
    }

}
