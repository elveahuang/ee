package cc.elvea.platform.system.security.api.impl;

import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.security.api.ClientApi;
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
public class ClientApiImpl implements ClientApi {

    private final ClientService clientService;

    /**
     * @see ClientApi#save(ClientDto)
     */
    @Override
    public void save(ClientDto clientDto) {
    }

    /**
     * @see ClientApi#findById(Long)
     */
    @Override
    public ClientDto findById(Long id) {
        return SpringUtils.getBean(ClientConverter.class).entity2Dto(this.clientService.findById(id));
    }

    /**
     * @see ClientApi#findByClientId(String)
     */
    @Override
    public ClientDto findByClientId(String clientId) {
        return SpringUtils.getBean(ClientConverter.class).entity2Dto(this.clientService.findClientByClientId(clientId));
    }

}
