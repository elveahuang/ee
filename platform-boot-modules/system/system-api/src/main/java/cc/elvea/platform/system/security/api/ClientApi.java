package cc.elvea.platform.system.security.api;

import cc.elvea.platform.system.security.model.dto.ClientDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface ClientApi {

    void save(ClientDto clientDto);

    ClientDto findById(Long id);

    ClientDto findByClientId(String clientId);

}
