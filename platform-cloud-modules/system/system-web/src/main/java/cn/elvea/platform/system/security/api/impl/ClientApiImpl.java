package cn.elvea.platform.system.security.api.impl;

import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.security.api.ClientApi;
import cn.elvea.platform.system.security.model.converter.ClientConverter;
import cn.elvea.platform.system.security.model.dto.ClientDto;
import cn.elvea.platform.system.security.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 0.0.1
 */
@RestController
@AllArgsConstructor
public class ClientApiImpl implements ClientApi {

    private final ClientService clientService;

    /**
     * @see ClientApi#save(ClientDto)
     */
    @Override
    public R<?> save(ClientDto clientDto) {
        return R.success();
    }

    /**
     * @see ClientApi#findById(Long)
     */
    @Override
    public R<ClientDto> findById(Long id) {
        return R.success(ClientConverter.INSTANCE.entity2Dto(this.clientService.findById(id)));
    }

    /**
     * @see ClientApi#findByClientId(String)
     */
    @Override
    public R<ClientDto> findByClientId(String clientId) {
        return R.success(ClientConverter.INSTANCE.entity2Dto(this.clientService.findClientByClientId(clientId)));
    }

}
