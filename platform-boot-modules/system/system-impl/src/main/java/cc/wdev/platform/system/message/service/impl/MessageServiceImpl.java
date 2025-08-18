package cc.wdev.platform.system.message.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseEntityService;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.system.message.domain.entity.MessageEntity;
import cc.wdev.platform.system.message.domain.entity.MessageEntity_;
import cc.wdev.platform.system.message.enums.MessageStatusEnum;
import cc.wdev.platform.system.message.repository.MessageRepository;
import cc.wdev.platform.system.message.service.MessageService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
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
                predicates.add(root.get(MessageEntity_.STATUS).in(statusValueList));
            }
            predicates.add(builder.equal(root.get(MessageEntity_.ACTIVE), Boolean.TRUE));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

}
