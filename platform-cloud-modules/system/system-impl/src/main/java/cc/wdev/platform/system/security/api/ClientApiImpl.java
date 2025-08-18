package cc.wdev.platform.system.security.api;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.security.domain.converter.ClientConverter;
import cc.wdev.platform.system.security.domain.dto.ClientDto;
import cc.wdev.platform.system.security.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
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
        return R.success(SpringUtils.getBean(ClientConverter.class).entity2Dto(this.clientService.findById(id)));
    }

    /**
     * @see ClientApi#findByClientId(String)
     */
    @Override
    public R<ClientDto> findByClientId(String clientId) {
        return R.success(SpringUtils.getBean(ClientConverter.class).entity2Dto(this.clientService.findClientByClientId(clientId)));
    }

}
