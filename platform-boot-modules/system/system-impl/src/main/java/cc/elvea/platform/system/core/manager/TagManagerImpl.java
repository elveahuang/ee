package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.core.model.converter.TagConverter;
import cc.elvea.platform.system.core.model.entity.TagEntity;
import cc.elvea.platform.system.core.model.form.TagForm;
import cc.elvea.platform.system.core.model.request.TagRelationRequest;
import cc.elvea.platform.system.core.model.request.TagRelationSaveRequest;
import cc.elvea.platform.system.core.model.request.TagSearchRequest;
import cc.elvea.platform.system.core.model.request.TagTypeRequest;
import cc.elvea.platform.system.core.model.vo.TagRelationVo;
import cc.elvea.platform.system.core.model.vo.TagTypeVo;
import cc.elvea.platform.system.core.model.vo.TagVo;
import cc.elvea.platform.system.core.service.TagRelationService;
import cc.elvea.platform.system.core.service.TagService;
import cc.elvea.platform.system.core.service.TagTypeService;
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
public class TagManagerImpl implements TagManager {

    private final TagService tagService;

    private final TagTypeService tagTypeService;

    private final TagRelationService tagRelationService;

    /**
     * @see TagManager#getTagType(TagTypeRequest)
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
     * @see TagManager#search(TagSearchRequest)
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
     * @see TagManager#save(TagForm)
     */
    @Override
    public void save(TagForm form) {
        TagEntity entity = SpringUtils.getBean(TagConverter.class).form2Entity(form);
        this.tagService.save(entity);
    }

    /**
     * @see TagManager#getRelation(TagRelationRequest)
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
     * @see TagManager#saveRelation(TagRelationSaveRequest)
     */
    @Override
    public void saveRelation(TagRelationSaveRequest request) {
        this.tagRelationService.saveRelation(request);
    }

}
