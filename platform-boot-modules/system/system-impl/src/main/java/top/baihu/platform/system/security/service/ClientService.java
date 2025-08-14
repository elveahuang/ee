package top.baihu.platform.system.security.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.commons.service.EntityService;
import top.baihu.platform.system.security.domain.entity.ClientEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
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
