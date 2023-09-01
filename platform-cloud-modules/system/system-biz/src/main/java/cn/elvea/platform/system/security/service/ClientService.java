package cn.elvea.platform.system.security.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.security.model.entity.ClientEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 * @since 0.0.1
 */
public interface ClientService extends CachingEntityService<ClientEntity, Long> {

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return {@link ClientEntity}
     */
    ClientEntity findClientById(Long id);

    /**
     * 根据客户端标识查询
     *
     * @param clientId 客户端标识
     * @return {@link ClientEntity}
     */
    ClientEntity findClientByClientId(String clientId);

}
