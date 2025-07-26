package cc.elvea.platform.system.security.manager.impl;

import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.security.manager.ClientManager;
import cc.elvea.platform.system.security.model.converter.ClientConverter;
import cc.elvea.platform.system.security.model.dto.ClientDto;
import cc.elvea.platform.system.security.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class ClientManagerImpl implements ClientManager {

    private final ClientService clientService;

    /**
     * @see ClientManager#save(ClientDto)
     */
    @Override
    public void save(ClientDto clientDto) {
    }

    /**
     * @see ClientManager#findById(Long)
     */
    @Override
    public ClientDto findById(Long id) {
        return SpringUtils.getBean(ClientConverter.class).entity2Dto(this.clientService.findById(id));
    }

    /**
     * @see ClientManager#findByClientId(String)
     */
    @Override
    public ClientDto findByClientId(String clientId) {
        return SpringUtils.getBean(ClientConverter.class).entity2Dto(this.clientService.findClientByClientId(clientId));
    }

}
