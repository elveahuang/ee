package top.baihu.platform.system.security.manager;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.security.domain.converter.ClientConverter;
import top.baihu.platform.system.security.domain.dto.ClientDto;
import top.baihu.platform.system.security.service.ClientService;

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
