package cn.elvea.platform.system.security.api.impl;

import cn.elvea.platform.system.security.api.ClientApi;
import cn.elvea.platform.system.security.model.converter.ClientConverter;
import cn.elvea.platform.system.security.model.dto.ClientDto;
import cn.elvea.platform.system.security.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
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
        return ClientConverter.INSTANCE.entity2Dto(this.clientService.findById(id));
    }

    /**
     * @see ClientApi#findByClientId(String)
     */
    @Override
    public ClientDto findByClientId(String clientId) {
        return ClientConverter.INSTANCE.entity2Dto(this.clientService.findClientByClientId(clientId));
    }

}
