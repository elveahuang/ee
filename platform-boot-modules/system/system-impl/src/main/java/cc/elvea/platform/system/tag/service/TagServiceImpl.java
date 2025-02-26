package cc.elvea.platform.system.tag.service;

import cc.elvea.platform.commons.data.jdbc.utils.JdbcUtils;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.tag.model.entity.TagEntity;
import cc.elvea.platform.system.tag.model.entity.TagEntity_;
import cc.elvea.platform.system.tag.model.entity.TagRelationEntity;
import cc.elvea.platform.system.tag.model.request.TagRelationRequest;
import cc.elvea.platform.system.tag.model.request.TagSearchRequest;
import cc.elvea.platform.system.tag.repository.TagRepository;
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
public class TagServiceImpl
        extends BaseCachingEntityService<TagEntity, Long, TagRepository>
        implements TagService {

    private final TagRelationService tagRelationService;

    /**
     * @see TagService#search(TagSearchRequest)
     */
    @Override
    public Page<TagEntity> search(TagSearchRequest request) {
        Specification<TagEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(TagEntity_.TYPE_ID), request.getTypeId()));
            if (StringUtils.isNotEmpty(request.getQ())) {
                predicates.add(builder.like(root.get(TagEntity_.TITLE), JdbcUtils.generateLike(request.getQ())));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification, request.getPageable());
    }

    /**
     * @see TagService#findByTypeId(Long)
     */
    @Override
    public List<TagEntity> findByTypeId(Long typeId) {
        Specification<TagEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(TagEntity_.TYPE_ID), typeId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    /**
     * @see TagService#findByTarget(TagRelationRequest)
     */
    @Override
    public List<TagEntity> findByTarget(TagRelationRequest request) {
        List<TagRelationEntity> relationList = this.tagRelationService.findRelations(request);

        List<TagEntity> tagList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(relationList)) {
            tagList.addAll(this.findByIds(relationList.stream().map(TagRelationEntity::getItemId).toList()));
        }
        return tagList;
    }

}
