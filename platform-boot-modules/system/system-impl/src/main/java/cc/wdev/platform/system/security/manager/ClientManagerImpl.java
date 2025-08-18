package cc.wdev.platform.system.security.manager;

import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.security.domain.converter.ClientConverter;
import cc.wdev.platform.system.security.domain.dto.ClientDto;
import cc.wdev.platform.system.security.service.ClientService;
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
