package cc.wdev.platform.system.message.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseEntityService;
import cc.wdev.platform.system.message.domain.dto.MessageRecipientDto;
import cc.wdev.platform.system.message.domain.dto.MessageSenderDto;
import cc.wdev.platform.system.message.domain.entity.MessageUserEntity;
import cc.wdev.platform.system.message.domain.entity.MessageUserEntity_;
import cc.wdev.platform.system.message.repository.MessageUserRepository;
import cc.wdev.platform.system.message.service.MessageUserService;
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
public class MessageUserServiceImpl
    extends BaseEntityService<MessageUserEntity, Long, MessageUserRepository>
    implements MessageUserService {

    /**
     * @see MessageUserService#findByMessage(Long)
     */
    @Override
    public List<MessageUserEntity> findByMessage(Long messageId) {
        Specification<MessageUserEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(MessageUserEntity_.MESSAGE_ID), messageId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    /**
     * @see MessageUserService#findByMessage(Long)
     */
    @Override
    public MessageSenderDto getSender(Long id) {
        return this.getSender(this.findById(id));
    }

    /**
     * @see MessageUserService#findByMessage(Long)
     */
    @Override
    public MessageSenderDto getSender(MessageUserEntity entity) {
        MessageSenderDto.MessageSenderDtoBuilder builder = MessageSenderDto.builder();
        if (entity != null) {
            builder.id(entity.getUserId());
            builder.username(entity.getUsername());
            builder.email(entity.getEmail());
            builder.mobileCountryCode(entity.getMobileCountryCode());
            builder.mobileNumber(entity.getMobileNumber());
        }
        return builder.build();
    }

    /**
     * @see MessageUserService#getRecipient(Long)
     */
    @Override
    public MessageRecipientDto getRecipient(Long id) {
        return this.getRecipient(this.findById(id));
    }

    /**
     * @see MessageUserService#getRecipient(MessageUserEntity)
     */
    @Override
    public MessageRecipientDto getRecipient(MessageUserEntity entity) {
        MessageRecipientDto.MessageRecipientDtoBuilder builder = MessageRecipientDto.builder();
        if (entity != null) {
            builder.id(entity.getUserId());
            builder.username(entity.getUsername());
            builder.email(entity.getEmail());
            builder.mobileCountryCode(entity.getMobileCountryCode());
            builder.mobileNumber(entity.getMobileNumber());
        }
        return builder.build();
    }

}
