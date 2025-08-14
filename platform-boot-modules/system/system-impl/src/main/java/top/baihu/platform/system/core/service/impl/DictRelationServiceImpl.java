package top.baihu.platform.system.core.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.commons.utils.ObjectUtils;
import top.baihu.platform.commons.utils.StringUtils;
import top.baihu.platform.system.core.domain.entity.DictRelationEntity;
import top.baihu.platform.system.core.domain.entity.DictRelationEntity_;
import top.baihu.platform.system.core.domain.request.DictRelationDeleteRequest;
import top.baihu.platform.system.core.domain.request.DictRelationRequest;
import top.baihu.platform.system.core.domain.request.DictRelationSaveRequest;
import top.baihu.platform.system.core.repository.DictRelationRepository;
import top.baihu.platform.system.core.service.DictRelationService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictRelationServiceImpl
    extends BaseCachingEntityService<DictRelationEntity, Long, DictRelationRepository>
    implements DictRelationService {

    /**
     * @see DictRelationService#findRelations(DictRelationRequest)
     */
    @Override
    public List<DictRelationEntity> findRelations(DictRelationRequest request) {
        Specification<DictRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(DictRelationEntity_.TARGET_ID), request.getTargetId()));
            predicates.add(builder.equal(root.get(DictRelationEntity_.TARGET_TYPE), request.getTargetType()));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    /**
     * @see DictRelationService#saveRelation(DictRelationSaveRequest)
     */
    @Override
    public void saveRelation(DictRelationSaveRequest request) {
        if (ObjectUtils.isValidId(request.getTargetId()) && StringUtils.isNotEmpty(request.getTargetType())) {
            this.deleteRelation(DictRelationRequest.builder().targetId(request.getTargetId()).targetType(request.getTargetType()).build());
        }
        List<DictRelationEntity> entityList = Arrays.stream(request.getIds()).map((id) -> DictRelationEntity.builder()
            .targetId(request.getTargetId()).targetType(request.getTargetType())
            .typeId(request.getTypeId()).itemId(id)
            .build()
        ).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(entityList)) {
            saveBatch(entityList);
            log.info("TagRelation Save success.");
        }
    }

    /**
     * @see DictRelationService#deleteRelation(DictRelationRequest)
     */
    @Override
    public void deleteRelation(DictRelationRequest request) {
        List<DictRelationEntity> relationEntityList = this.findRelations(request);
        if (CollectionUtils.isNotEmpty(relationEntityList)) {
            this.deleteBatch(relationEntityList);
        }
    }

    /**
     * @see DictRelationService#deleteRelation(DictRelationDeleteRequest)
     */
    @Override
    public void deleteRelation(DictRelationDeleteRequest request) {
        Specification<DictRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(DictRelationEntity_.TYPE_ID), request.getTypeId()));
            predicates.add(builder.equal(root.get(DictRelationEntity_.ITEM_ID), request.getId()));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

}
