package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.commons.utils.ObjectUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import cc.wdev.platform.system.core.domain.entity.TagRelationEntity;
import cc.wdev.platform.system.core.domain.entity.TagRelationEntity_;
import cc.wdev.platform.system.core.domain.request.TagRelationDeleteRequest;
import cc.wdev.platform.system.core.domain.request.TagRelationRequest;
import cc.wdev.platform.system.core.domain.request.TagRelationSaveRequest;
import cc.wdev.platform.system.core.repository.TagRelationRepository;
import cc.wdev.platform.system.core.service.TagRelationService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.DeleteSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class TagRelationServiceImpl
    extends BaseCachingEntityService<TagRelationEntity, Long, TagRelationRepository>
    implements TagRelationService {

    /**
     * @see TagRelationService#findRelations(TagRelationRequest)
     */
    @Override
    public List<TagRelationEntity> findRelations(TagRelationRequest request) {
        Specification<TagRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(TagRelationEntity_.TARGET_ID), request.getTargetId()));
            predicates.add(builder.equal(root.get(TagRelationEntity_.TARGET_TYPE), request.getTargetType()));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    /**
     * @see TagRelationService#saveRelation(TagRelationSaveRequest)
     */
    @Override
    public void saveRelation(TagRelationSaveRequest request) {
        if (ObjectUtils.isValidId(request.getTargetId()) && StringUtils.isNotEmpty(request.getTargetType())) {
            this.deleteRelation(TagRelationRequest.builder().targetId(request.getTargetId()).targetType(request.getTargetType()).build());
        }
        List<TagRelationEntity> entityList = Arrays.stream(request.getIds()).map((id) -> TagRelationEntity.builder()
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
     * @see TagRelationService#deleteRelation(TagRelationRequest)
     */
    @Override
    public void deleteRelation(TagRelationRequest request) {
        List<TagRelationEntity> relationEntityList = this.findRelations(request);
        if (CollectionUtils.isNotEmpty(relationEntityList)) {
            this.deleteBatch(relationEntityList);
        }
    }

    /**
     * @see TagRelationService#deleteRelation(TagRelationDeleteRequest)
     */
    @Override
    public void deleteRelation(TagRelationDeleteRequest request) {
        DeleteSpecification<TagRelationEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(TagRelationEntity_.ITEM_ID), request.getTagId()));
            predicates.add(builder.equal(root.get(TagRelationEntity_.TYPE_ID), request.getTagTypeId()));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        this.repository.delete(specification);
    }

}
