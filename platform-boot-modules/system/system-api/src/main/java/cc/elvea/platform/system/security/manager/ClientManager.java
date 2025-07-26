package cc.elvea.platform.system.security.manager;

import cc.elvea.platform.system.security.model.dto.ClientDto;

/**
 * @author elvea
 */
public interface ClientManager {

    void save(ClientDto clientDto);

    ClientDto findById(Long id);

    ClientDto findByClientId(String clientId);

}
