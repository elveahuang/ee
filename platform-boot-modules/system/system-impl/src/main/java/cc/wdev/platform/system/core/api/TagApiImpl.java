package cc.wdev.platform.system.core.api;

import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.core.domain.converter.TagConverter;
import cc.wdev.platform.system.core.domain.entity.TagEntity;
import cc.wdev.platform.system.core.domain.form.TagForm;
import cc.wdev.platform.system.core.domain.request.TagRelationRequest;
import cc.wdev.platform.system.core.domain.request.TagRelationSaveRequest;
import cc.wdev.platform.system.core.domain.request.TagSearchRequest;
import cc.wdev.platform.system.core.domain.request.TagTypeRequest;
import cc.wdev.platform.system.core.domain.vo.TagRelationVo;
import cc.wdev.platform.system.core.domain.vo.TagTypeVo;
import cc.wdev.platform.system.core.domain.vo.TagVo;
import cc.wdev.platform.system.core.service.TagRelationService;
import cc.wdev.platform.system.core.service.TagService;
import cc.wdev.platform.system.core.service.TagTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TagApiImpl implements TagApi {

    private final TagService tagService;

    private final TagTypeService tagTypeService;

    private final TagRelationService tagRelationService;

    /**
     * @see TagApi#getTagType(TagTypeRequest)
     */
    @Override
    public TagTypeVo getTagType(TagTypeRequest request) {
        TagTypeVo vo = tagTypeService.getTagType(request.getType());
        if (vo != null && request.isWithItem()) {
            List<TagEntity> tagList = this.tagService.findByTypeId(vo.getId());
            if (CollectionUtils.isNotEmpty(tagList)) {
                vo.setItems(tagList.stream().map(SpringUtils.getBean(TagConverter.class)::entity2Vo).toList());
            }
        }
        return vo;
    }

    /**
     * @see TagApi#search(TagSearchRequest)
     */
    @Override
    public Page<TagVo> search(TagSearchRequest request) {
        // 指定类型ID
        request.setTypeId(tagTypeService.getTagTypeId(request.getType()));

        Page<TagEntity> page = this.tagService.search(request);
        if (CollectionUtils.isNotEmpty(page.getContent())) {
            List<TagVo> itemList = page.getContent().stream().map(SpringUtils.getBean(TagConverter.class)::entity2Vo).toList();
            return new PageImpl<>(itemList, request.getPageable(), page.getTotalElements());
        }
        return new PageImpl<>(Collections.emptyList(), request.getPageable(), 0);
    }

    /**
     * @see TagApi#save(TagForm)
     */
    @Override
    public void save(TagForm form) {
        TagEntity entity = SpringUtils.getBean(TagConverter.class).form2Entity(form);
        this.tagService.save(entity);
    }

    /**
     * @see TagApi#getRelation(TagRelationRequest)
     */
    @Override
    public TagRelationVo getRelation(TagRelationRequest request) {
        TagRelationVo vo = TagRelationVo.builder().targetType(request.getTargetType()).targetId(request.getTargetId()).build();

        List<TagEntity> tagList = this.tagService.findByTarget(request);
        if (CollectionUtils.isNotEmpty(tagList)) {
            vo.setItems(tagList.stream().map(SpringUtils.getBean(TagConverter.class)::entity2Vo).toList());
        }
        return vo;
    }

    /**
     * @see TagApi#saveRelation(TagRelationSaveRequest)
     */
    @Override
    public void saveRelation(TagRelationSaveRequest request) {
        this.tagRelationService.saveRelation(request);
    }

}
