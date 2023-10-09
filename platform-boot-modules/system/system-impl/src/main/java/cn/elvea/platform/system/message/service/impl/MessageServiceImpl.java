package cn.elvea.platform.system.message.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseEntityService;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.message.enums.MessageStatusEnum;
import cn.elvea.platform.system.message.model.entity.MessageEntity;
import cn.elvea.platform.system.message.model.entity.MessageEntity_;
import cn.elvea.platform.system.message.repository.MessageRepository;
import cn.elvea.platform.system.message.service.MessageService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
@Service
public class MessageServiceImpl
        extends BaseEntityService<MessageEntity, Long, MessageRepository>
        implements MessageService {

    /**
     * @see MessageService#findByStatus(List)
     */
    @Override
    public List<MessageEntity> findByStatus(final List<MessageStatusEnum> statusList) {
        Specification<MessageEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(statusList)) {
                List<Integer> statusValueList = statusList.stream().map(MessageStatusEnum::getValue).toList();
                predicates.add(root.get(MessageEntity_.status).in(statusValueList));
            }
            predicates.add(builder.equal(root.get(MessageEntity_.active), Boolean.TRUE));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

}
